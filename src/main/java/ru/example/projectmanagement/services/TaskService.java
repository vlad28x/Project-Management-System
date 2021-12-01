package ru.example.projectmanagement.services;

import ru.example.projectmanagement.dto.TaskFilterDTO;
import ru.example.projectmanagement.entities.Task;

import java.util.List;

/**
 * Service for task.
 */
public interface TaskService {

    /**
     * Get all tasks.
     *
     * @return list of tasks
     */
    List<Task> getAll();

    /**
     * Get specific tasks.
     *
     * @param id - identifier of task
     * @return task entity
     */
    Task getById(Long id);

    /**
     * Add task.
     *
     * @param task - task entity
     * @return task entity
     */
    Task add(Task task);

    /**
     * Update task.
     *
     * @param task - task entity
     * @return task entity
     */
    Task update(Task task);

    /**
     * Delete specific task.
     *
     * @param id - identifier of task
     */
    void delete(Long id);

    /**
     * Assign the user to the task.
     *
     * @param taskId - identifier of task
     * @param userId - identifier of user
     * @return task entity
     */
    Task assignUser(Long taskId, Long userId);

    /**
     * Assign the release to the task.
     *
     * @param taskId    - identifier of task
     * @param releaseId - identifier of release
     * @return task entity
     */
    Task assignRelease(Long taskId, Long releaseId);

    /**
     * Complete specific task.
     *
     * @param id - identifier of task
     * @return task entity
     */
    Task complete(Long id);

    /**
     * Filter tasks.
     *
     * @param taskFilter - specific filter
     * @return list of tasks
     */
    List<Task> filterTask(TaskFilterDTO taskFilter);
}
