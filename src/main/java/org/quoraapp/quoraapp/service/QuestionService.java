package org.quoraapp.quoraapp.service;

import lombok.RequiredArgsConstructor;
import org.quoraapp.quoraapp.dto.QuestionRequestDTO;
import org.quoraapp.quoraapp.dto.QuestionResponseDTO;
import org.quoraapp.quoraapp.mapper.QuestionMapper;
import org.quoraapp.quoraapp.model.Question;
import org.quoraapp.quoraapp.repository.QuestionRepository;
import org.springframework.stereotype.Service;
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
}
