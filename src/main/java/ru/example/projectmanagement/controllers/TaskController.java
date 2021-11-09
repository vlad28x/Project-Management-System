package ru.example.projectmanagement.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.TaskRequestDto;
import ru.example.projectmanagement.dto.TaskResponseDto;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Задачи", description = "Взаимодействие с задачами")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Operation(summary = "Получение всех задач", description = "Позволяет получить все задачи")
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        List<TaskResponseDto> list = new ArrayList<>();
        list.add(new TaskResponseDto());
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Получение одной задачи", description = "Позволяет получить одну задачу по заданному ID")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(new TaskResponseDto());
    }

    @Operation(summary = "Добавление задачи", description = "Позволяет добавить задачу")
    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto newTask) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new TaskResponseDto());
    }

    @Operation(summary = "Обновление задачи", description = "Позволяет обновить задачу по заданному ID")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto newTask) {
        return ResponseEntity.ok(new TaskResponseDto());
    }

    @Operation(summary = "Удаление задачи", description = "Позволяет удалить задачу по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
    }
}
