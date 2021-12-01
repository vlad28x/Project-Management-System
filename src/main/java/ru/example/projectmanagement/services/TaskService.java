package ru.example.projectmanagement.services;

import ru.example.projectmanagement.dto.TaskFilterDTO;
import ru.example.projectmanagement.dto.TaskRequestDto;
import ru.example.projectmanagement.dto.TaskResponseDto;
import ru.example.projectmanagement.entities.Task;

import java.util.List;

/**
 * Service for task.
 */
public interface TaskService {

    /**
     * Get all tasks.
     *
     * @return list of TaskResponseDto
     */
    List<TaskResponseDto> getAll();

    /**
     * Get specific tasks.
     *
     * @param id - identifier of task
     * @return TaskResponseDto
     */
    TaskResponseDto getById(Long id);

    /**
     * Add task.
     *
     * @param newTask - TaskRequestDto
     * @return TaskResponseDto
     */
    TaskResponseDto add(TaskRequestDto newTask);

    /**
     * Update task.
     *
     * @param newTask - TaskRequestDto
     * @return TaskResponseDto
     */
    TaskResponseDto update(TaskRequestDto newTask);

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
     * @return TaskResponseDto
     */
    TaskResponseDto assignUser(Long taskId, Long userId);

    /**
     * Assign the release to the task.
     *
     * @param taskId    - identifier of task
     * @param releaseId - identifier of release
     * @return TaskResponseDto
     */
    TaskResponseDto assignRelease(Long taskId, Long releaseId);

    /**
     * Complete specific task.
     *
     * @param id - identifier of task
     * @return TaskResponseDto
     */
    TaskResponseDto complete(Long id);

    /**
     * Filter tasks.
     *
     * @param taskFilter - specific filter
     * @return list of TaskResponseDto
     */
    List<TaskResponseDto> filterTask(TaskFilterDTO taskFilter);
}
