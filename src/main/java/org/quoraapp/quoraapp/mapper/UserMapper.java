package org.quoraapp.quoraapp.mapper;

import org.quoraapp.quoraapp.dto.UserResponseDTO;
import org.quoraapp.quoraapp.model.User;

public class UserMapper {

    public static UserResponseDTO toUserResponseDTO(User user) {

        return UserResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
