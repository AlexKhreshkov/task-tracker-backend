package com.example.task_tracker_backend.converters;

import com.example.task_tracker_backend.contracts.TaskContract;
import com.example.task_tracker_backend.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {

    public TaskContract toContract(Task task) {
        return TaskContract
                .builder()
                .id(task.getId())
                .title(task.getTitle())
                .text(task.getText())
                .userId(task.getUser() != null ? task.getUser().getId() : null)
                .createdAt(task.getCreatedAt())
                .status(task.getStatus())
                .build();
    }

}
