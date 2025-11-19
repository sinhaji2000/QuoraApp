package org.quoraapp.quoraapp.service;

import lombok.RequiredArgsConstructor;
import org.quoraapp.quoraapp.dto.QuestionRequestDTO;
import org.quoraapp.quoraapp.dto.QuestionResponseDTO;
import org.quoraapp.quoraapp.mapper.QuestionMapper;
import org.quoraapp.quoraapp.model.Question;
import org.quoraapp.quoraapp.repository.QuestionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepository;
    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO){

        Question question = Question.builder()
                .title(questionRequestDTO.getTitle())
                .content(questionRequestDTO.getContent())
                .cratedAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return questionRepository.save(question)
                .map(QuestionMapper ::toQuestionResponseDTO)
                .doOnSuccess(response -> System.out.println("Created question: " + question))
                .doOnError(error -> System.out.println("Error creating question: " + question));

                    // ||
                    // ||

//        Mono<Question> questionMono = questionRepository.save(question);
//        Mono<QuestionResponseDTO> questionResponseDTOMono = questionMono.map(QuestionMapper::toQuestionResponseDTO);

    }

    @Override
    public Flux<QuestionResponseDTO> SearchQuestion(String searchTerm , int offset , int page){

        return questionRepository.findByTitleOrContentContainingIgnoreCase(searchTerm , PageRequest.of(offset , page))
                .map(QuestionMapper::toQuestionResponseDTO)
                .doOnError(error -> System.out.println("Error searching question: " + searchTerm))
                .doOnComplete(() -> System.out.println("All questions found: " + questionRepository.count())) ;

        //return null ;
    }
}
