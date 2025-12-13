package org.quoraapp.quoraapp.service;

import org.quoraapp.quoraapp.dto.AnswerRequestDTO;
import org.quoraapp.quoraapp.dto.AnswerResponseDTO;
import org.quoraapp.quoraapp.model.Answer;
import reactor.core.publisher.Mono;

public interface IAnswerService {

    Mono<AnswerResponseDTO>createAnswer(AnswerRequestDTO answerRequestDTO);
    Mono<AnswerResponseDTO> findAnswerById(String id);
}
