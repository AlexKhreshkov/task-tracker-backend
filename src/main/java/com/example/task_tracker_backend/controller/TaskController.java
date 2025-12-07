package com.example.task_tracker_backend.controller;

import com.example.task_tracker_backend.contracts.TaskContract;
import com.example.task_tracker_backend.entities.Task;
import com.example.task_tracker_backend.security.CustomUserDetails;
import com.example.task_tracker_backend.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody TaskContract taskContract, @AuthenticationPrincipal CustomUserDetails user) {
        return taskService.createTask(taskContract, user.getUser());
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }

//    @GetMapping
//    public List<TaskContract> getAllTasks() {
//        return taskService.getAllTasks();
//    }

    @GetMapping
    public List<TaskContract> getCurrentUserTasks(@AuthenticationPrincipal CustomUserDetails user) {
        return taskService.getTasksByUserId(user.getUser().getId());
    }

    @GetMapping("/user/{userId}")
    public List<TaskContract> getTasksByUserId(@PathVariable Long userId) {
        return taskService.getTasksByUserId(userId);
    }
}
