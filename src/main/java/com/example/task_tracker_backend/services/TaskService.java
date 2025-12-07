package com.example.task_tracker_backend.services;

import com.example.task_tracker_backend.contracts.TaskContract;
import com.example.task_tracker_backend.converters.TaskConverter;
import com.example.task_tracker_backend.entities.Task;
import com.example.task_tracker_backend.entities.Users;
import com.example.task_tracker_backend.repo.Status;
import com.example.task_tracker_backend.repo.TaskRepo;
import com.example.task_tracker_backend.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepo taskRepo;
    private final TaskConverter taskConverter;

    public TaskService(TaskRepo taskRepo, TaskConverter taskConverter) {
        this.taskRepo = taskRepo;
        this.taskConverter = taskConverter;
    }

    public Task createTask(TaskContract taskContract, Users user) {
        Task task = Task
                .builder()
                .title(taskContract.getTitle())
                .text(taskContract.getText())
                .user(user)
                .createdAt(Instant.now())
                .status(taskContract.getStatus() != null ? taskContract.getStatus() : Status.CREATED)
                .build();

        return taskRepo.saveAndFlush(task);
    }

    public List<TaskContract> getAllTasks() {
         return taskRepo.findAll().stream().map(taskConverter::toContract).toList();
    }

    public List<TaskContract> getTasksByUserId(Long userId) {
        if (userId == null) {
            return taskRepo.findByUserIsNull().stream().map(taskConverter::toContract).toList();
        }
        return taskRepo.findByUserId(userId).stream().map(taskConverter::toContract).toList();
    }

    public void deleteTask(Long taskId) {
        taskRepo.deleteById(taskId);
    }
}
