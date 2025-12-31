package org.quoraapp.quoraapp.producers;

import lombok.RequiredArgsConstructor;
import org.quoraapp.quoraapp.config.KafkaConfig;
import org.quoraapp.quoraapp.events.ViewCountEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;


    public void publishViewCountEvent(ViewCountEvent viewCountEvent) {

        kafkaTemplate.send(KafkaConfig.TOPIC_NAME , viewCountEvent.getTargetId() ,  viewCountEvent)
                .whenComplete((result , err) -> {
                    if(err != null) {
                        System.out.println(err.getMessage());
                    }

                }) ;
    }


}
