package ru.example.projectmanagement.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.ProjectRequestDto;
import ru.example.projectmanagement.dto.ProjectResponseDto;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Проекты", description = "Взаимодействие с проектами")
@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Operation(summary = "Получение всех проектов", description = "Позволяет получить все проекты")
    @GetMapping
    public List<ProjectResponseDto> getAllProjects() {
        List<ProjectResponseDto> list = new ArrayList<>();
        list.add(new ProjectResponseDto());
        return list;
    }

    @Operation(summary = "Получение одного проекта", description = "Позволяет получить один проект по заданному ID")
    @GetMapping("/{id}")
    public ProjectResponseDto getProjectById(@PathVariable Long id) {
        return new ProjectResponseDto();
    }

    @Operation(summary = "Добавление проекта", description = "Позволяет добавить проект")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponseDto createProject(@RequestBody ProjectRequestDto newProject) {
        return new ProjectResponseDto();
    }

    @Operation(summary = "Обновление проекта", description = "Позволяет обновить проект по заданному ID")
    @PutMapping("/{id}")
    public ProjectResponseDto updateProject(@PathVariable Long id, @RequestBody ProjectRequestDto newProject) {
        return new ProjectResponseDto();
    }

    @Operation(summary = "Удаление проекта", description = "Позволяет удалить проект по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
    }
}
