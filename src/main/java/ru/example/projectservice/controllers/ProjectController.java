package ru.example.projectservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.example.projectservice.dto.ProjectRequestDto;
import ru.example.projectservice.dto.ProjectResponseDto;
import ru.example.projectservice.services.ProjectService;

import java.util.List;

@PreAuthorize("hasAnyRole('PROGRAMMER')")
@Tag(name = "Проекты", description = "Взаимодействие с проектами")
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Получение всех проектов", description = "Позволяет получить все проекты")
    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAll());
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Получение одного проекта", description = "Позволяет получить один проект по заданному ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getById(id));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Добавление проекта", description = "Позволяет добавить проект")
    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto newProject) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.add(newProject));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Обновление проекта", description = "Позволяет обновить проект по заданному ID")
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable Long id, @RequestBody ProjectRequestDto newProject) {
        projectService.getById(id);
        newProject.setId(id);
        return ResponseEntity.ok(projectService.update(newProject));

    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Удаление проекта", description = "Позволяет удалить проект по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.delete(id);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Завершение проекта", description = "Позволяет завершить проект по заданному ID")
    @PutMapping("{id}/complete")
    public ResponseEntity<ProjectResponseDto> completeProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.complete(id));
    }

    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @PutMapping("/pay")
    public ResponseEntity<ProjectResponseDto> payDebt(@RequestHeader("Authorization") String jwt) {
        return ResponseEntity.ok(projectService.pay(jwt));
    }
}
