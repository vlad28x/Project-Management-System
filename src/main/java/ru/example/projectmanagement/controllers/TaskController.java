package ru.example.projectmanagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.TaskRequestDTO;
import ru.example.projectmanagement.dto.TaskResponseDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        List<TaskResponseDTO> list = new ArrayList<>();
        list.add(new TaskResponseDTO());
        return list;
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable Long id) {
        return new TaskResponseDTO();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDTO createTask(@RequestBody TaskRequestDTO newTask) {
        return new TaskResponseDTO();
    }

    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO newTask) {
        return new TaskResponseDTO();
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
    }
}
