package com.example.task_tracker_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public static final String NOT_FOUND_MESSAGE = "Not found.";

    public NotFoundException() {
        super(NOT_FOUND_MESSAGE);
    }

    public NotFoundException(String message) {
       super(message == null ? NOT_FOUND_MESSAGE : message);
    }
}
