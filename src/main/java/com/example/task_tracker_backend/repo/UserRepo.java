package com.example.task_tracker_backend.repo;

import com.example.task_tracker_backend.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);
}
