package org.quoraapp.quoraapp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponseDTO {


    private String id;
    private String authorId ;
    private String title ;
    private String content ;
    private LocalDateTime createdAt ;
}
