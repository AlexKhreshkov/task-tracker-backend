package com.example.task_tracker_backend.repo;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty(value = "TODO")
    TODO,
    @JsonProperty(value = "IN_PROGRESS")
    IN_PROGRESS,
    @JsonProperty(value = "DONE")
    DONE
}
