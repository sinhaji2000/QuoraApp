package org.quoraapp.quoraapp.service;

import lombok.RequiredArgsConstructor;
import org.quoraapp.quoraapp.dto.QuestionRequestDTO;
import org.quoraapp.quoraapp.dto.QuestionResponseDTO;
import org.quoraapp.quoraapp.events.ViewCountEvent;
import org.quoraapp.quoraapp.mapper.QuestionMapper;
import org.quoraapp.quoraapp.model.Question;
import org.quoraapp.quoraapp.model.QuestionElasticDocument;
import org.quoraapp.quoraapp.producers.KafkaEventProducer;
import org.quoraapp.quoraapp.repository.QuestionDocumentRepository;
import org.quoraapp.quoraapp.repository.QuestionRepository;
import org.quoraapp.quoraapp.utils.CursorUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepository;
    private final KafkaEventProducer kafkaEventProducer;
    private final IQuestionIndexService questionIndexService;
    private final QuestionDocumentRepository questionDocumentRepository;
    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO){



        Question question = Question.builder()

                .title(questionRequestDTO.getTitle())

                .content(questionRequestDTO.getContent())
                .authorId(questionRequestDTO.getAuthorId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return questionRepository.save(question)
                .map(savedQuestion -> {
                    questionIndexService.createQuestionIndex(savedQuestion); // dumping questionb to Elastic search
                    return QuestionMapper.toQuestionResponseDTO(savedQuestion);
                })
                .doOnSuccess(response -> System.out.println("Created question: " + question))
                .doOnError(error -> System.out.println("Error creating question: " + question));

                    // ||
                    // ||

//        Mono<Question> questionMono = questionRepository.save(question);
//        Mono<QuestionResponseDTO> questionResponseDTOMono = questionMono.map(QuestionMapper::toQuestionResponseDTO);

    }

    @Override
    public Flux<QuestionResponseDTO> SearchQuestion(String searchTerm , int offset , int page){


        // Offset pagination
        return questionRepository.findByTitleOrContentContainingIgnoreCase(searchTerm , PageRequest.of(offset , page))
                .map(QuestionMapper::toQuestionResponseDTO)
                .doOnError(error -> System.out.println("Error searching question: " + searchTerm))
                .doOnComplete(() -> System.out.println("All questions found: " + questionRepository.count())) ;

        //return null ;
    }

    @Override
    public Flux<QuestionResponseDTO>getAllQuestions(String cursor , int size){

        Pageable pageable = PageRequest.of(0, size);

        if(!CursorUtils.isCursorValid(cursor)){
            return questionRepository.findTop10ByOrderByCreatedAtAsc()
                    .take(size)
                    .map(QuestionMapper::toQuestionResponseDTO)
                    .doOnError(error -> System.out.println("Error searching questions: " + cursor))
                    .doOnComplete(() -> System.out.println("All questions found: " + questionRepository.count()));
        }else{

            LocalDateTime cursorTimeStamp =CursorUtils.parseCursor(cursor);
            return questionRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(cursorTimeStamp, pageable)
                    .map(QuestionMapper::toQuestionResponseDTO)
                    .doOnComplete(() -> System.out.println("All questions found: " + questionRepository.count()))
                    .doOnError(error -> System.out.println("Error searching question: " + cursor)) ;


        }
    }
    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String id) {
        return questionRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Question not found with id: " + id
                )))
                .map(QuestionMapper::toQuestionResponseDTO)
                .doOnSuccess(response -> {
                    System.out.println("Found question: " + id);
                    ViewCountEvent viewCountEvent = new ViewCountEvent(
                            id,
                            "question",
                            LocalDateTime.now()
                    );
                    kafkaEventProducer.publishViewCountEvent(viewCountEvent);
                })
                .doOnError(error ->
                        System.out.println("Error searching question: " + id + " - " + error.getMessage())
                );
    }

    @Override
    public List<QuestionElasticDocument> searchQuestionByElasticSearch(String query){

        return questionDocumentRepository.findByTitleContainingOrContentContaining(query , query) ;
    }
}
