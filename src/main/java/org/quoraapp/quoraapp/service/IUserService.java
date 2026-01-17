package org.quoraapp.quoraapp.service;

import org.quoraapp.quoraapp.dto.UserRequestDTO;
import org.quoraapp.quoraapp.dto.UserResponseDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface IUserService {

    public Mono<UserResponseDTO> createUser(UserRequestDTO userRequestDTO);
    public Mono<UserResponseDTO> followUser(String userId , String loginUser);
}
