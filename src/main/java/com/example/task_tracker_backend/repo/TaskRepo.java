package com.example.task_tracker_backend.repo;

import com.example.task_tracker_backend.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Stream;

public interface TaskRepo extends JpaRepository<Task, Long> {
    
    List<Task> findByUserId(Long userId);
    List<Task> findByUserIsNull();
}
