package ru.example.projectmanagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.ProjectRequestDTO;
import ru.example.projectmanagement.dto.ProjectResponseDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @GetMapping
    public List<ProjectResponseDTO> getAllProjects() {
        List<ProjectResponseDTO> list = new ArrayList<>();
        list.add(new ProjectResponseDTO());
        return list;
    }

    @GetMapping("/{id}")
    public ProjectResponseDTO getProjectById(@PathVariable Long id) {
        return new ProjectResponseDTO();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponseDTO createProject(@RequestBody ProjectRequestDTO newProject) {
        return new ProjectResponseDTO();
    }

    @PutMapping("/{id}")
    public ProjectResponseDTO updateProject(@PathVariable Long id, @RequestBody ProjectRequestDTO newProject) {
        return new ProjectResponseDTO();
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
    }
}
