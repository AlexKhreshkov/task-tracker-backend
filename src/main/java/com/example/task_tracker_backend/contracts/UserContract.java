package com.example.task_tracker_backend.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserContract {

    @JsonProperty("username")
    private String username;
}
