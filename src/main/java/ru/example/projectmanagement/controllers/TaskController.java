package ru.example.projectmanagement.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.TaskRequestDTO;
import ru.example.projectmanagement.dto.TaskResponseDTO;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Задачи", description = "Взаимодействие с задачами")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Operation(summary = "Получение всех задач", description = "Позволяет получить все задачи")
    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        List<TaskResponseDTO> list = new ArrayList<>();
        list.add(new TaskResponseDTO());
        return list;
    }

    @Operation(summary = "Получение одной задачи", description = "Позволяет получить одну задачу по заданному ID")
    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable Long id) {
        return new TaskResponseDTO();
    }

    @Operation(summary = "Добавление задачи", description = "Позволяет добавить задачу")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDTO createTask(@RequestBody TaskRequestDTO newTask) {
        return new TaskResponseDTO();
    }

    @Operation(summary = "Обновление задачи", description = "Позволяет обновить задачу по заданному ID")
    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO newTask) {
        return new TaskResponseDTO();
    }

    @Operation(summary = "Удаление задачи", description = "Позволяет удалить задачу по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
    }
}
