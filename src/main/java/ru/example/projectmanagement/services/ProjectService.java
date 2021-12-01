package ru.example.projectmanagement.services;

import ru.example.projectmanagement.entities.Project;

import java.util.List;

/**
 * Service for project.
 */
public interface ProjectService {

    /**
     * Get all projects.
     *
     * @return list of projects
     */
    List<Project> getAll();

    /**
     * Get specific project.
     *
     * @param id - identifier of project
     * @return project entity
     */
    Project getById(Long id);

    /**
     * Add project.
     *
     * @param project - project entity
     * @return project entity
     */
    Project add(Project project);

    /**
     * Update project.
     *
     * @param project - project entity
     * @return project entity
     */
    Project update(Project project);

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
     * @return project entity
     */
    Project complete(Long id);
}
