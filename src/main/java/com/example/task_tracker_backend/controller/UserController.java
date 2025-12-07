package com.example.task_tracker_backend.controller;

import com.example.task_tracker_backend.security.CustomUserDetails;
import com.example.task_tracker_backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("api/user")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal CustomUserDetails user) {
        try {
            return ResponseEntity
                    .ok()
                    .body(userService.getUser(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

}
