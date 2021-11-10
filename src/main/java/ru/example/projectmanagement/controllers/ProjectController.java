package ru.example.projectmanagement.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.ProjectRequestDto;
import ru.example.projectmanagement.dto.ProjectResponseDto;
import ru.example.projectmanagement.entities.Project;
import ru.example.projectmanagement.services.ProjectService;
import ru.example.projectmanagement.utils.mappers.ProjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Проекты", description = "Взаимодействие с проектами")
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper = ProjectMapper.INSTANCE;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "Получение всех проектов", description = "Позволяет получить все проекты")
    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
        List<ProjectResponseDto> list = projectService.getAll().stream()
                .map(projectMapper::projectToProjectResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Получение одного проекта", description = "Позволяет получить один проект по заданному ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id) {
        Project project = projectService.getById(id);
        return ResponseEntity.ok(projectMapper.projectToProjectResponseDto(project));
    }

    @Operation(summary = "Добавление проекта", description = "Позволяет добавить проект")
    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto newProject) {
        Project project = projectMapper.projectRequestDtoToProject(newProject);
        project = projectService.add(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectMapper.projectToProjectResponseDto(project));
    }

    @Operation(summary = "Обновление проекта", description = "Позволяет обновить проект по заданному ID")
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable Long id, @RequestBody ProjectRequestDto newProject) {
        Project project = projectMapper.projectRequestDtoToProject(newProject);
        project = projectService.add(project);
        return ResponseEntity.ok(projectMapper.projectToProjectResponseDto(project));

    }

    @Operation(summary = "Удаление проекта", description = "Позволяет удалить проект по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.delete(id);
    }
}
