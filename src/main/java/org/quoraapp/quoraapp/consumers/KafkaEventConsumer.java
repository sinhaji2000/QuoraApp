package org.quoraapp.quoraapp.consumers;

import lombok.RequiredArgsConstructor;
import org.quoraapp.quoraapp.config.KafkaConfig;
import org.quoraapp.quoraapp.events.ViewCountEvent;
import org.quoraapp.quoraapp.repository.QuestionRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventConsumer {

    private final QuestionRepository questionRepository;

    @KafkaListener(  // ✓ CORRECT - parentheses
            topics = KafkaConfig.TOPIC_NAME,  // ✓ comma, not semicolon
            groupId = "view-count-consumer",  // ✓ comma
            containerFactory = "kafkaListenerContainerFactory"  // ✓ no semicolon at end
    )
    public void handleViewCountEvent(ViewCountEvent viewCountEvent) {

        questionRepository.findById(viewCountEvent.getTargetId())
                .flatMap(question -> {
                    Integer views = question.getViews();
                    question.setViews(views == null ? 0 : views+1);
                    return questionRepository.save(question);
                }).subscribe(updateQuestion -> {
                    System.out.println("Updated view count: " + updateQuestion.getId());
                } , error -> {
                    System.out.println("Error while updating view count: " + error.getMessage());
                });
    }

}
