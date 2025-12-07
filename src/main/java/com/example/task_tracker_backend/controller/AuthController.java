package com.example.task_tracker_backend.controller;

import com.example.task_tracker_backend.contracts.JwtResponse;
import com.example.task_tracker_backend.contracts.LoginContract;
import com.example.task_tracker_backend.exception.ConflictException;
import com.example.task_tracker_backend.security.JwtUtil;
import com.example.task_tracker_backend.services.CustomUserDetailsService;
import com.example.task_tracker_backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, 
                         JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody LoginContract loginContract) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginContract.getUsername(),
                            loginContract.getPassword()
                    )
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginContract.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody LoginContract loginContract) {

        userService.validateUserCred(loginContract.getUsername(), loginContract.getPassword());
        if (this.userService.findByUsername(loginContract.getUsername()).isPresent()) {
            throw new ConflictException("User with " + loginContract.getUsername() + " login already exists");
        }

        if (this.userService.findByEmail(loginContract.getEmail()).isPresent()) {
            throw new ConflictException("User with " + loginContract.getEmail() + " email already exists");
        }

        try {
            userService.createUser(loginContract);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginContract.getUsername(),
                            loginContract.getPassword()
                    )
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginContract.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

}
