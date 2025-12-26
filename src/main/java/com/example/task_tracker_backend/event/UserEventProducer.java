package com.example.task_tracker_backend.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserEventProducer {

    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    public UserEventProducer(KafkaTemplate<String, UserCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserCreatedEvent(UserCreatedEvent userCreatedEvent) {
        log.info("Sending new user email {} to EMAIL_SENDING_TASKS topic", userCreatedEvent.getEmail());
        kafkaTemplate.send("EMAIL_SENDING_TASKS", userCreatedEvent);
    }
}
