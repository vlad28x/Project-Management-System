package ru.example.projectmanagement.services;

import ru.example.projectmanagement.dto.ProjectRequestDto;
import ru.example.projectmanagement.dto.ProjectResponseDto;
import ru.example.projectmanagement.entities.Project;

import java.util.List;

/**
 * Service for project.
 */
public interface ProjectService {

    /**
     * Get all projects.
     *
     * @return list of ProjectResponseDto
     */
    List<ProjectResponseDto> getAll();

    /**
     * Get specific project.
     *
     * @param id - identifier of project
     * @return ProjectResponseDto
     */
    ProjectResponseDto getById(Long id);

    /**
     * Add project.
     *
     * @param newProject - ProjectRequestDto
     * @return ProjectResponseDto
     */
    ProjectResponseDto add(ProjectRequestDto newProject);

    /**
     * Update project.
     *
     * @param newProject - ProjectRequestDto
     * @return ProjectResponseDto
     */
    ProjectResponseDto update(ProjectRequestDto newProject);

    /**
     * Delete specific project.
     *
     * @param id - identifier of project
     */
    void delete(Long id);

    /**
     * Complete specific project.
     *
     * @param id - identifier of project
     * @return ProjectResponseDto
     */
    ProjectResponseDto complete(Long id);
}
