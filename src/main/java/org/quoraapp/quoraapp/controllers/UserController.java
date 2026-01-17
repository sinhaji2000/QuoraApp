package org.quoraapp.quoraapp.controllers;

import lombok.RequiredArgsConstructor;
import org.quoraapp.quoraapp.dto.UserRequestDTO;
import org.quoraapp.quoraapp.dto.UserResponseDTO;
import org.quoraapp.quoraapp.service.IUserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final IUserService userService;

    @PostMapping
    Mono<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO)
                .doOnNext(user -> user.setPassword(userRequestDTO.getPassword()))
                .doOnError(throwable -> System.out.println(throwable.getMessage()));
    }

    @PostMapping("/follow/{id}")
    Mono<UserResponseDTO> followUser(@PathVariable String id , @RequestBody String loginUser) {
        return userService.followUser(id , loginUser)
                .doOnSuccess(response -> System.out.println(response))
                .doOnError(throwable -> System.out.println(throwable.getMessage()));
    }
}
