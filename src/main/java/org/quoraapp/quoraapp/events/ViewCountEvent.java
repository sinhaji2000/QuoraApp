package org.quoraapp.quoraapp.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewCountEvent {

    private String targetId ;
    private String targetType ;
    private LocalDateTime timestamp ;


}
