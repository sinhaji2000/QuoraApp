package org.quoraapp.quoraapp.service;


import lombok.RequiredArgsConstructor;
import org.quoraapp.quoraapp.dto.AnswerRequestDTO;
import org.quoraapp.quoraapp.dto.AnswerResponseDTO;
import org.quoraapp.quoraapp.mapper.AnswerMapper;
import org.quoraapp.quoraapp.model.Answer;
import org.quoraapp.quoraapp.repository.AnswerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Service
public class AnswerService implements IAnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO) {

       Answer answer = Answer.builder()
               .content(answerRequestDTO.getContent())
               .questionId(answerRequestDTO.getQuestionId())
               .createdAt(LocalDateTime.now())
               .updatedAt(LocalDateTime.now())
               .build();


       return answerRepository.save(answer)
               .map(AnswerMapper ::toAnswerResponseDTO)
               .doOnSuccess(response -> System.out.println("Answer saved successfully"))
               .doOnError(error -> System.out.println("Error while saving answer: " + error.getMessage()));

    }

    @Override
    public Mono<AnswerResponseDTO>getAnswerById(String id){

        return answerRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Answer not found with id: " + id
                )))
                .map(AnswerMapper :: toAnswerResponseDTO)
                .doOnSuccess(response -> System.out.println("Answer found successfully"))
                .doOnError(error -> System.out.println("Error while saving answer: " + error.getMessage()));

    }


    public Mono<AnswerResponseDTO>updateAnswer(String id, AnswerRequestDTO answerRequestDTO) {

        return answerRepository.findById(id)
                .flatMap(existingAnswer -> {
                    existingAnswer.setContent(answerRequestDTO.getContent());
                    return answerRepository.save(existingAnswer) ;
                })
                .map(AnswerMapper :: toAnswerResponseDTO)
                .doOnSuccess(response -> System.out.println("update Answer"))
                .doOnError(err -> System.out.println(err)) ;
    }

    public Mono<AnswerResponseDTO> deleteAnswer(String id) {
        return answerRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Answer not found")))
                .flatMap(answer ->
                        answerRepository.deleteById(id)
                                .thenReturn(answer)  // Keep the answer to map to DTO
                )
                .map(AnswerMapper::toAnswerResponseDTO);
    }

}
