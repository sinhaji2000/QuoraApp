package org.quoraapp.quoraapp.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequestDTO {

    @NotBlank(message = "Target ID should not be blank")
    private String targetId ;

    @NotBlank(message = "Target type should not be blank")
    private String targetType ;

    @NotNull(message = "not null")
    private Boolean isLiked ;
}
