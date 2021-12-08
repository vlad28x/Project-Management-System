package ru.example.projectservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.example.projectservice.dto.TaskFilterDTO;
import ru.example.projectservice.dto.TaskRequestDto;
import ru.example.projectservice.dto.TaskResponseDto;
import ru.example.projectservice.services.TaskService;

import java.util.List;

@Tag(name = "Задачи", description = "Взаимодействие с задачами")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Получение всех задач", description = "Позволяет получить все задачи")
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Получение одной задачи", description = "Позволяет получить одну задачу по заданному ID")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Добавление задачи", description = "Позволяет добавить задачу")
    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto newTask) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.add(newTask));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Обновление задачи", description = "Позволяет обновить задачу по заданному ID")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto newTask) {
        taskService.getById(id);
        newTask.setId(id);
        return ResponseEntity.ok(taskService.update(newTask));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Удаление задачи", description = "Позволяет удалить задачу по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.delete(id);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Назначение исполнителя",
            description = "Позволяет назначить исполнителя по заданному ID для задачи по заданному ID")
    @PutMapping("{taskId}/assign/user/{userId}")
    public ResponseEntity<TaskResponseDto> assignUser(@PathVariable Long taskId, @PathVariable Long userId) {
        return ResponseEntity.ok(taskService.assignUser(taskId, userId));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Назначение релиза",
            description = "Позволяет назначить релиз по заданному ID для задачи по заданному ID")
    @PutMapping("{taskId}/assign/release/{releaseId}")
    public ResponseEntity<TaskResponseDto> assignRelease(@PathVariable Long taskId, @PathVariable Long releaseId) {
        return ResponseEntity.ok(taskService.assignRelease(taskId, releaseId));
    }

    @PreAuthorize("hasRole('PROGRAMMER')")
    @Operation(summary = "Завершение задачи", description = "Позволяет завершить задачу по заданному ID")
    @PutMapping("{id}/complete")
    public ResponseEntity<TaskResponseDto> completeTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.complete(id));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Фильтрация задач", description = "Позволяет фильтровать задачи по строковым типам и перечислениям")
    @GetMapping("/filter")
    public ResponseEntity<List<TaskResponseDto>> filterTasks(@RequestBody TaskFilterDTO taskFilter) {
        return ResponseEntity.ok(taskService.filterTask(taskFilter));
    }
}
