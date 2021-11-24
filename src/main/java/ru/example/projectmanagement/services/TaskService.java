package ru.example.projectmanagement.services;

import ru.example.projectmanagement.dto.TaskFilterDTO;
import ru.example.projectmanagement.entities.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAll();

    Task getById(Long id);

    Task add(Task task);

    Task update(Task task);

    void delete(Long id);

    Task assignUser(Long taskId, Long userId);

    Task assignRelease(Long taskId, Long releaseId);

    Task complete(Long id);

    List<Task> filterTask(TaskFilterDTO taskFilter);
}
