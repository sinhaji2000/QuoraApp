package org.quoraapp.quoraapp.service;

import org.quoraapp.quoraapp.dto.QuestionRequestDTO;
import org.quoraapp.quoraapp.dto.QuestionResponseDTO;
import org.quoraapp.quoraapp.model.Question;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);
}
