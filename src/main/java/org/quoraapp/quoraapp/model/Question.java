package org.quoraapp.quoraapp.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "questions")
public class Question {

    @Id
    private String id;

    @NotBlank(message = "Title is required")
    @Size(min = 10, max = 100 , message = "A title must be between 10 to 100 character")
    private String title ;

    @NotBlank(message = "Title is required")
    @Size(min = 10, max = 100 , message = "A title must be between 10 to 100 character")
    private String content ;

    private String authorId ;

    @CreatedDate
    private LocalDateTime cratedAt ;

    @LastModifiedDate
    private LocalDateTime updatedAt ;
}
