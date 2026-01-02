package org.quoraapp.quoraapp.mapper;

import org.quoraapp.quoraapp.dto.AnswerResponseDTO;
import org.quoraapp.quoraapp.model.Answer;

public class AnswerMapper {

    public static AnswerResponseDTO toAnswerResponseDTO(Answer answer){

        return AnswerResponseDTO.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .createdAt(answer.getCreatedAt())
                .updatedAt(answer.getUpdatedAt())
                .build() ;
    }
}
