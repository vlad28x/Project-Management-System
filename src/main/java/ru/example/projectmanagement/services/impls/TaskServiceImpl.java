package ru.example.projectmanagement.services.impls;

import org.springframework.stereotype.Service;
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

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Задача не найдена", id));
    }

    @Override
    public Task add(Task task) {
        try {
            return taskRepository.save(task);
        } catch (RuntimeException e) {
            throw new BadRequestException("Неверный запрос");
        }
    }

    @Override
    public Task update(Task task) {
        try {
            return taskRepository.save(task);
        } catch (RuntimeException e) {
            throw new BadRequestException("Неверный запрос");
        }
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
