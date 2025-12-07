package com.example.task_tracker_backend.services;

import com.example.task_tracker_backend.exception.ValidationException;
import org.springframework.stereotype.Service;

import static com.example.task_tracker_backend.utils.ExceptionUtils.requireNonNull;

@Service
public class UserValidationService {

    private final static int EMAIL_MIN_LENGTH = 3;
    private final static int PASSWORD_MIN_LENGTH = 3;

    private void validateEmail(String email) {
        requireNonNull(email, ValidationException::new, "Email is null");
        if (email.strip().length() < EMAIL_MIN_LENGTH) {
            throw new ValidationException("Email is less then 3 symbols");
        }
    }

    private void validatePassword(String password) {
        requireNonNull(password, ValidationException::new, "Password is null");
        if (password.strip().length() < PASSWORD_MIN_LENGTH) {
            throw new ValidationException("Password is less then 3 symbols");
        }
    }

    public void validateUserCred(String email, String password) {
        validateEmail(email);
        validatePassword(password);
    }
}