package com.example.task_tracker_backend.services;

import com.example.task_tracker_backend.contracts.LoginContract;
import com.example.task_tracker_backend.contracts.UserContract;
import com.example.task_tracker_backend.entities.Users;
import com.example.task_tracker_backend.exception.NotFoundException;
import com.example.task_tracker_backend.repo.UserRepo;
import com.example.task_tracker_backend.security.CustomUserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.task_tracker_backend.utils.ExceptionUtils.requireNonNull;

@Service
public class UserService {

    private final UserValidationService userValidationService;
    private final PasswordService passwordService;
    private final UserRepo userRepo;

    public UserService(UserValidationService userValidationService, PasswordService passwordService, UserRepo userRepo) {
        this.userValidationService = userValidationService;
        this.passwordService = passwordService;
        this.userRepo = userRepo;
    }

    public Users getUserById(Long id) {
        return requireNonNull(this.userRepo.findById(id).get(), NotFoundException::new, "User not found");
    }

    public void validateUserCred(String username, String password) {
        userValidationService.validateUserCred(username, password);
    }

    public void createUser(LoginContract loginContract) {
        validateUserCred(loginContract.getUsername(), loginContract.getPassword());

        Users user = new Users();
        user.setUsername(loginContract.getUsername());
        user.setPassword(passwordService.encodePassword(loginContract.getPassword()));
        this.userRepo.saveAndFlush(user);
    }

    public Optional<Users> findByUsername(String username) {
        return this.userRepo.findByUsername(username);
    }

    public Optional<Users> findByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    public UserContract getUser(CustomUserDetails user) {
        return UserContract.builder()
                .email(user.getUser().getEmail())
                .build();
    }
}
