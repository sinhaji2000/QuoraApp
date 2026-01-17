package org.quoraapp.quoraapp.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.quoraapp.quoraapp.dto.QuestionRequestDTO;
import org.quoraapp.quoraapp.dto.QuestionResponseDTO;
import org.quoraapp.quoraapp.model.QuestionElasticDocument;
import org.quoraapp.quoraapp.service.IQuestionService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/question")
public class QuestionController {

    private final IQuestionService questionService;

    @PostMapping()
    public Mono<QuestionResponseDTO> createQuestion(@Valid @RequestBody QuestionRequestDTO questionRequestDTO) {
        return questionService.createQuestion(questionRequestDTO)
                .doOnSuccess(response -> System.out.println("Created question: " + questionRequestDTO))
                .doOnError(error -> System.out.println("Error creating question: " + questionRequestDTO));
    }

    @GetMapping("/search")
    public Flux<QuestionResponseDTO> SearchQuestion(@RequestParam String query , @RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "10") int size){

        return questionService.SearchQuestion(query, page, size);
    }


    @GetMapping
    public Flux<QuestionResponseDTO>getAllQuestions(@RequestParam (required = false) String cursor , @RequestParam(defaultValue="10")int size){

        return questionService.getAllQuestions(cursor , size) ;

    }
    @GetMapping("/{id}")
    public Mono<QuestionResponseDTO> getQuestionById(@PathVariable  String id){
        return questionService.getQuestionById(id);
    }

    @GetMapping("/elasticsearch")
    public List<QuestionElasticDocument> searchQuestionByElasticSearch(@RequestParam String query){

        return questionService.searchQuestionByElasticSearch(query);
    }

}
