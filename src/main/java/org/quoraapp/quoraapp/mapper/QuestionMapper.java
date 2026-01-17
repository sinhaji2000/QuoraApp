package org.quoraapp.quoraapp.mapper;

import org.quoraapp.quoraapp.dto.QuestionRequestDTO;
import org.quoraapp.quoraapp.dto.QuestionResponseDTO;
import org.quoraapp.quoraapp.model.Question;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class QuestionMapper {

    public static QuestionResponseDTO toQuestionResponseDTO(Question question) {

        return QuestionResponseDTO.builder()
                .id(question.getId())
                .userId(question.getUserId())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .build();


    }
}
