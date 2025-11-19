package org.quoraapp.quoraapp.service;

import org.quoraapp.quoraapp.controllers.QuestionController;
import org.quoraapp.quoraapp.dto.QuestionRequestDTO;
import org.quoraapp.quoraapp.dto.QuestionResponseDTO;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);
    public Flux<QuestionResponseDTO> SearchQuestion(String searchTerm , int offset, int page) ;
}
