package ru.example.projectmanagement.services;

import ru.example.projectmanagement.entities.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAll();

    Task getById(Long id);

    Task add(Task task);

    Task update(Task task);

    void delete(Long id);
}
