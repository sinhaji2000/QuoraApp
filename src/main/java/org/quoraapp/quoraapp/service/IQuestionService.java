package org.quoraapp.quoraapp.service;


import org.quoraapp.quoraapp.dto.QuestionRequestDTO;
import org.quoraapp.quoraapp.dto.QuestionResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);
    public Flux<QuestionResponseDTO> SearchQuestion(String searchTerm , int offset, int page) ;
    public Flux<QuestionResponseDTO>getAllQuestions(String cursor , int size) ;
    public Mono<QuestionResponseDTO> getQuestionById(String id);
}
