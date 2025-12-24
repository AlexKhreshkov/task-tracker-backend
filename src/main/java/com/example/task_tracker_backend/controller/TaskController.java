package com.example.task_tracker_backend.controller;

import com.example.task_tracker_backend.contracts.TaskContract;
import com.example.task_tracker_backend.converters.TaskConverter;
import com.example.task_tracker_backend.entities.Task;
import com.example.task_tracker_backend.security.CustomUserDetails;
import com.example.task_tracker_backend.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;
    private final TaskConverter taskConverter;

    public TaskController(TaskService taskService, TaskConverter taskConverter) {
        this.taskService = taskService;
        this.taskConverter = taskConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody TaskContract taskContract, @AuthenticationPrincipal CustomUserDetails user) {
        return taskService.createTask(taskContract, user.getUser());
    }

    @GetMapping("/{taskId}")
    public TaskContract getTask(@PathVariable Long taskId, @AuthenticationPrincipal CustomUserDetails user) {
        Task task = taskService.getTaskById(taskId);
        if (!taskService.isUsersTask(task, user.getUser())) {
         throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return taskConverter.toContract(task);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId, @AuthenticationPrincipal CustomUserDetails user) {
        Task task = taskService.getTaskById(taskId);
        if (!taskService.isUsersTask(task, user.getUser())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
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

    @PatchMapping("/{taskId}")
    public void updateTask(@PathVariable Long taskId, @RequestBody TaskContract taskContract, @AuthenticationPrincipal CustomUserDetails user) {
        Task task = taskService.getTaskById(taskId);
        if (!taskService.isUsersTask(task, user.getUser())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        taskService.updateTask(taskId, taskContract);
    }

    @PatchMapping("status/{taskId}")
    public void updateTaskStatus(@PathVariable Long taskId, @RequestBody TaskContract taskContract, @AuthenticationPrincipal CustomUserDetails user) {
        Task task = taskService.getTaskById(taskId);
        if (!taskService.isUsersTask(task, user.getUser())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        taskService.updateTaskStatus(taskId, taskContract.getStatus());
    }
}
