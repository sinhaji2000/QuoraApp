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
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "answers")
public class Answer {

    @Id
    private String id;

    @Size(min = 1, max = 100)
    @NotBlank(message = "content is requires")
    private String content ;

    @Indexed
    private String questionId ;

    @CreatedDate
    @Indexed
    private LocalDateTime createdAt ;

    @LastModifiedDate
    private LocalDateTime updatedAt ;
}
