package org.quoraapp.quoraapp.controllers;


import lombok.RequiredArgsConstructor;
import org.quoraapp.quoraapp.dto.QuestionRequestDTO;
import org.quoraapp.quoraapp.dto.QuestionResponseDTO;
import org.quoraapp.quoraapp.service.IQuestionService;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/question")
public class QuestionController {

    private final IQuestionService questionService;

    @PostMapping()
    public Mono<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {
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

}
