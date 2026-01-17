package org.quoraapp.quoraapp.service;


import lombok.RequiredArgsConstructor;
import org.quoraapp.quoraapp.dto.UserRequestDTO;
import org.quoraapp.quoraapp.dto.UserResponseDTO;
import org.quoraapp.quoraapp.mapper.UserMapper;
import org.quoraapp.quoraapp.model.User;
import org.quoraapp.quoraapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public Mono<UserResponseDTO> createUser(UserRequestDTO userRequestDTO) {

        User user = User.builder()
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .password(userRequestDTO.getPassword())
                .build();

        return userRepository.save(user)
                .map(savedUser -> {
                    return UserMapper.toUserResponseDTO(savedUser) ;
                })
                .doOnSuccess(response -> {
                    System.out.println(response);
                })
                .doOnError(throwable -> {
                    System.out.println(throwable.getMessage());
                }) ;
    }


    public Mono<UserResponseDTO> followUser(String userId , String loginUser) {


        return userRepository.findById(loginUser)
                .switchIfEmpty(Mono.error(new RuntimeException("Login user not found")))
                .flatMap(loginUserObj -> {
                    loginUserObj.getFollwing().add(userId) ;
                    return userRepository.save(loginUserObj) ;
                })
                .map(savedUser -> UserMapper.toUserResponseDTO(savedUser) )
                .doOnSuccess(response -> {System.out.println(response);})
                .doOnError(throwable -> {System.out.println(throwable.getMessage());}) ;

    }
}
