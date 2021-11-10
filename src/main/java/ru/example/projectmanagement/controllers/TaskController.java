package ru.example.projectmanagement.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @Operation(summary = "Получение всех задач", description = "Позволяет получить все задачи")
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        List<TaskResponseDto> list = taskService.getAll().stream()
                .map(taskMapper::taskToTaskResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Получение одной задачи", description = "Позволяет получить одну задачу по заданному ID")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return ResponseEntity.ok(taskMapper.taskToTaskResponseDto(task));
    }

    @Operation(summary = "Добавление задачи", description = "Позволяет добавить задачу")
    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto newTask) {
        Task task = taskMapper.taskRequestDtoToTask(newTask);
        task = taskService.add(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskMapper.taskToTaskResponseDto(task));
    }

    @Operation(summary = "Обновление задачи", description = "Позволяет обновить задачу по заданному ID")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto newTask) {
        Task task = taskMapper.taskRequestDtoToTask(newTask);
        task = taskService.add(task);
        return ResponseEntity.ok(taskMapper.taskToTaskResponseDto(task));
    }

    @Operation(summary = "Удаление задачи", description = "Позволяет удалить задачу по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.delete(id);
    }
}
