package org.quoraapp.quoraapp.service;

import org.quoraapp.quoraapp.dto.AnswerRequestDTO;
import org.quoraapp.quoraapp.dto.AnswerResponseDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


public interface IAnswerService {

    Mono<AnswerResponseDTO>createAnswer(AnswerRequestDTO answerRequestDTO);
    Mono<AnswerResponseDTO>getAnswerById(String id) ;
    Mono<AnswerResponseDTO>updateAnswer(String id , AnswerRequestDTO answerRequestDTO);
    Mono<AnswerResponseDTO>deleteAnswer(String id);
    //Mono<AnswerResponseDTO> findAnswerById(String id);
}
