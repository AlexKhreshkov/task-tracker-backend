package com.example.task_tracker_backend.services;

import com.example.task_tracker_backend.exception.ValidationException;
import org.springframework.stereotype.Service;

import static com.example.task_tracker_backend.utils.ExceptionUtils.requireNonNull;

@Service
public class UserValidationService {

    private final static int USERNAME_MIN_LENGTH = 3;
    private final static int PASSWORD_MIN_LENGTH = 3;

    private void validateUsername(String username) {
        requireNonNull(username, ValidationException::new, "Username is null");
        if (username.strip().length() < USERNAME_MIN_LENGTH) {
            throw new ValidationException("Username is less then 3 symbols");
        }
    }

    private void validatePassword(String password) {
        requireNonNull(password, ValidationException::new, "Password is null");
        if (password.strip().length() < PASSWORD_MIN_LENGTH) {
            throw new ValidationException("Password is less then 3 symbols");
        }
    }

    public void validateUserCred(String username, String password) {
        validateUsername(username);
        validatePassword(password);
    }
}