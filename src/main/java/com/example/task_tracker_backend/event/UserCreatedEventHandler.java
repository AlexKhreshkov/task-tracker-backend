package com.example.task_tracker_backend.event;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserCreatedEventHandler {

    private final UserEventProducer userEventProducer;

    public UserCreatedEventHandler(UserEventProducer userEventProducer) {
        this.userEventProducer = userEventProducer;
    }

    public void sendCreatedUser(UserDetails userDetails) {
        UserCreatedEvent userCreatedEvent = UserCreatedEvent
                .builder()
                .email(userDetails.getUsername())
                .title("Task tracker Sign Up")
                .text("Welcome to Task Tracker app")
                .build();
        userEventProducer.sendUserCreatedEvent(userCreatedEvent);
    }
}
