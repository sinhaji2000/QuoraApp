package org.quoraapp.quoraapp.dto;

import java.time.LocalDateTime;

public class LikeResponseDTO {

    private String id ;

    private String targetId ;
    private String targetType ;
    private Boolean isLiked ;
    private LocalDateTime createdAt ;

}
