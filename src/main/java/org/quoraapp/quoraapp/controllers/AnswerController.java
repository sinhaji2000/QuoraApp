package org.quoraapp.quoraapp.controllers;



import lombok.RequiredArgsConstructor;
import org.quoraapp.quoraapp.dto.AnswerRequestDTO;
import org.quoraapp.quoraapp.dto.AnswerResponseDTO;
import org.quoraapp.quoraapp.service.IAnswerService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/answer")
public class AnswerController {

    private final IAnswerService answerService;

    @PostMapping
    public Mono<AnswerResponseDTO>createAnswer(@RequestBody AnswerRequestDTO answerRequestDTO){

        return answerService.createAnswer(answerRequestDTO)
                .doOnSuccess(respnse -> System.out.println("Answer created" + respnse))
                .doOnError(err -> System.out.println(err)) ;

    }


    @GetMapping("/{id}")
    public Mono<AnswerResponseDTO>getAnswerById(@PathVariable String id){

        return answerService.getAnswerById(id)
                .doOnSuccess(response -> System.out.println("get answer succesfully"))
                .doOnError(err -> System.out.println(err)) ;

    }

}


