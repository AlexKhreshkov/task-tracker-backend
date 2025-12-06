package com.example.task_tracker_backend.security;

import com.example.task_tracker_backend.entities.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;


import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class CustomUserDetails implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Users user;

    public CustomUserDetails(Users user) {
        this.user = user;
    }

    @Override
    public @NotNull Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    public Users getUser() {
        return user;
    }
}
