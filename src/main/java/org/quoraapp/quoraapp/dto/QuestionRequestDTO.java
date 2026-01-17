package org.quoraapp.quoraapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDTO {




    @NotBlank(message = "content is Requird")
    @Size(min = 10 , max = 100 , message = "content should be between 10 to 100 character")
    private String title ;

    @NotBlank(message = "content is Requird")
    @Size(min = 10 , max = 100 , message = "content should be between 10 to 100 character")
    private String content ;

    @NotBlank(message = "Unauthorised User")
    private String authorId ;

}
