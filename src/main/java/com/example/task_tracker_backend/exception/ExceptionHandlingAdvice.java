package com.example.task_tracker_backend.exception;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleException(NotFoundException ex) {
        return buildResponse(ex);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleException(ValidationException ex) {
        return buildResponse(ex);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleException(ConflictException ex) {
        return buildResponse(ex);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<Object> handleException(InternalServerError ex) {
        return buildResponse(ex);
    }

    private ResponseEntity<Object> buildResponse(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            status = responseStatus.value();
        }

        return ResponseEntity
                .status(status)
                .body(body);
    }
}
