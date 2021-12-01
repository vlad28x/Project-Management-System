package ru.example.projectmanagement.services;

import ru.example.projectmanagement.entities.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAll();

    Project getById(Long id);

    Project add(Project project);

    Project update(Project project);

    void delete(Long id);
}
