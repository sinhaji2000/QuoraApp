package org.quoraapp.quoraapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("follows")

public class Follow {

    private String id;
    private String followerId;   // A
    private String followingId;
}
