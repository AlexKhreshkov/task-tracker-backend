package com.example.task_tracker_backend.contracts;

import com.example.task_tracker_backend.repo.Status;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskContract {

    private Long id;
    private String title;
    private String text;
    private Status status;
    private Long userId;
    private Instant createdAt;
    private Instant doneAt;
}
