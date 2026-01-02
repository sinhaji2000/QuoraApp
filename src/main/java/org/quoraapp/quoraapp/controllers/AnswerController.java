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

    @PostMapping("update/{id}")
    public Mono<AnswerResponseDTO>updateAnswer(@PathVariable String id , @RequestBody AnswerRequestDTO answerRequestDTO){

        return answerService.updateAnswer(id ,  answerRequestDTO)
                .doOnSuccess(respnse -> System.out.println("update answer succesfully"))
                .doOnError(err -> System.out.println(err)) ;
    }

    @DeleteMapping("/{id}")
    public Mono<AnswerResponseDTO>deleteAnswer(@PathVariable String id){
        return answerService.deleteAnswer(id)
                .doOnSuccess(respnse -> System.out.println("delete answer succesfully"))
                .doOnError(err -> System.out.println(err)) ;
    }

}


