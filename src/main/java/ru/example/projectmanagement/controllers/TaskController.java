package ru.example.projectmanagement.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.TaskFilterDTO;
import ru.example.projectmanagement.dto.TaskRequestDto;
import ru.example.projectmanagement.dto.TaskResponseDto;
import ru.example.projectmanagement.entities.Task;
import ru.example.projectmanagement.services.TaskService;
import ru.example.projectmanagement.utils.mappers.TaskMapper;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Задачи", description = "Взаимодействие с задачами")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Получение всех задач", description = "Позволяет получить все задачи")
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        List<TaskResponseDto> list = taskService.getAll().stream()
                .map(taskMapper::taskToTaskResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Получение одной задачи", description = "Позволяет получить одну задачу по заданному ID")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return ResponseEntity.ok(taskMapper.taskToTaskResponseDto(task));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Добавление задачи", description = "Позволяет добавить задачу")
    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto newTask) {
        Task task = taskMapper.taskRequestDtoToTask(newTask);
        task = taskService.add(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskMapper.taskToTaskResponseDto(task));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Обновление задачи", description = "Позволяет обновить задачу по заданному ID")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto newTask) {
        Task task = taskMapper.taskRequestDtoToTask(newTask);
        task = taskService.add(task);
        return ResponseEntity.ok(taskMapper.taskToTaskResponseDto(task));
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
        Task task = taskService.assignUser(taskId, userId);
        return ResponseEntity.ok(taskMapper.taskToTaskResponseDto(task));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Назначение релиза",
            description = "Позволяет назначить релиз по заданному ID для задачи по заданному ID")
    @PutMapping("{taskId}/assign/release/{releaseId}")
    public ResponseEntity<TaskResponseDto> assignRelease(@PathVariable Long taskId, @PathVariable Long releaseId) {
        Task task = taskService.assignRelease(taskId, releaseId);
        return ResponseEntity.ok(taskMapper.taskToTaskResponseDto(task));
    }

    @PreAuthorize("hasRole('PROGRAMMER')")
    @Operation(summary = "Завершение задачи", description = "Позволяет завершить задачу по заданному ID")
    @PutMapping("{id}/complete")
    public ResponseEntity<TaskResponseDto> completeTask(@PathVariable Long id) {
        Task task = taskService.complete(id);
        return ResponseEntity.ok(taskMapper.taskToTaskResponseDto(task));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Фильтрация задач", description = "Позволяет фильтровать задачи по строковым типам и перечислениям")
    @GetMapping("/filter")
    public ResponseEntity<List<TaskResponseDto>> filterTasks(@RequestBody TaskFilterDTO taskFilter) {
        List<TaskResponseDto> list = taskService.filterTask(taskFilter).stream()
                .map(taskMapper::taskToTaskResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
