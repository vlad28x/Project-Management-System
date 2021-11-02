package ru.example.projectmanagement.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.ProjectRequestDTO;
import ru.example.projectmanagement.dto.ProjectResponseDTO;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Проекты", description = "Взаимодействие с проектами")
@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Operation(summary = "Получение всех проектов", description = "Позволяет получить все проекты")
    @GetMapping
    public List<ProjectResponseDTO> getAllProjects() {
        List<ProjectResponseDTO> list = new ArrayList<>();
        list.add(new ProjectResponseDTO());
        return list;
    }

    @Operation(summary = "Получение одного проекта", description = "Позволяет получить один проект по заданному ID")
    @GetMapping("/{id}")
    public ProjectResponseDTO getProjectById(@PathVariable Long id) {
        return new ProjectResponseDTO();
    }

    @Operation(summary = "Добавление проекта", description = "Позволяет добавить проект")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponseDTO createProject(@RequestBody ProjectRequestDTO newProject) {
        return new ProjectResponseDTO();
    }

    @Operation(summary = "Обновление проекта", description = "Позволяет обновить проект по заданному ID")
    @PutMapping("/{id}")
    public ProjectResponseDTO updateProject(@PathVariable Long id, @RequestBody ProjectRequestDTO newProject) {
        return new ProjectResponseDTO();
    }

    @Operation(summary = "Удаление проекта", description = "Позволяет удалить проект по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
    }
}
