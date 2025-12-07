package com.example.task_tracker_backend.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private String token;
}
