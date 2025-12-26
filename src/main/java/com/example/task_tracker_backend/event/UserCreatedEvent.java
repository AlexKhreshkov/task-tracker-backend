package com.example.task_tracker_backend.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserCreatedEvent {
    private String email;
    private String title;
    private String text;
}
