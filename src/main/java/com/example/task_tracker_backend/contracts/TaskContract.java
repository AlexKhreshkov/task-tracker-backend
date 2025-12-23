package com.example.task_tracker_backend.contracts;

import com.example.task_tracker_backend.repo.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskContract {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "text")
    private String text;

    @JsonProperty(value = "status")
    private Status status;

    @JsonProperty(value = "user_id")
    private Long userId;

    @JsonProperty(value = "created_at")
    private Instant createdAt;

    @JsonProperty(value = "done_at")
    private Instant doneAt;
}
