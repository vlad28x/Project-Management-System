package ru.example.projectmanagement.services.impls;

import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.projectmanagement.entities.Task;
import ru.example.projectmanagement.exceptions.BadRequestException;
import ru.example.projectmanagement.exceptions.NotFoundException;
import ru.example.projectmanagement.repositories.TaskRepository;
import ru.example.projectmanagement.services.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Task with ID %s not found", id)));
    }

    @Override
    public Task add(Task task) {
        try {
            return taskRepository.save(task);
        } catch (NestedRuntimeException e) {
            throw new BadRequestException("Bad request");
        }
    }

    @Override
    public Task update(Task task) {
        try {
            return taskRepository.save(task);
        } catch (NestedRuntimeException e) {
            throw new BadRequestException("Bad request");
        }
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
