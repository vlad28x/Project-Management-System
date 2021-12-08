package ru.example.projectservice.services.impls;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.projectservice.dto.TaskFilterDTO;
import ru.example.projectservice.dto.TaskRequestDto;
import ru.example.projectservice.dto.TaskResponseDto;
import ru.example.projectservice.exceptions.BadRequestException;
import ru.example.projectservice.exceptions.NotFoundException;
import ru.example.projectservice.repositories.TaskRepository;
import ru.example.projectservice.services.TaskService;
import ru.example.projectservice.services.specifications.TaskSpecification;
import ru.example.projectservice.utils.mappers.TaskMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
    private static final TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TaskResponseDto> getAll() {
        return taskRepository.findAll().stream()
                .map(taskMapper::taskToTaskResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public TaskResponseDto getById(Long id) {
        return taskMapper.taskToTaskResponseDto(taskRepository.findById(id).orElseThrow(() -> {
            log.error(String.format("Task with ID %s not found", id));
            return new NotFoundException(String.format("Task with ID %s not found", id));
        }));
    }

    @Override
    public TaskResponseDto add(TaskRequestDto newTask) {
        try {
            return taskMapper.taskToTaskResponseDto(taskRepository.save(
                    taskMapper.taskRequestDtoToTask(newTask)
            ));
        } catch (NestedRuntimeException e) {
            log.error(e.getMessage(), e.getCause());
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public TaskResponseDto update(TaskRequestDto newTask) {
        if (newTask == null || newTask.getId() == null) {
            log.error("Task or ID must not be null!");
            throw new BadRequestException("Task or ID must not be null!");
        }
        if (taskRepository.findById(newTask.getId()).isPresent()) {
            try {
                return taskMapper.taskToTaskResponseDto(taskRepository.save(
                        taskMapper.taskRequestDtoToTask(newTask)
                ));
            } catch (NestedRuntimeException e) {
                log.error(e.getMessage(), e.getCause());
                throw new BadRequestException(e.getMessage());
            }
        } else {
            log.error(String.format("Task with ID %s not found", newTask.getId()));
            throw new NotFoundException(String.format("Task with ID %s not found", newTask.getId()));
        }
    }

    @Override
    public void delete(Long id) {
        getById(id);
        taskRepository.deleteById(id);
    }

    @Transactional
    @Override
    public TaskResponseDto assignUser(Long taskId, Long userId) {
        try {
            int count = taskRepository.assignUser(taskId, userId);
            if (count == 0) {
                log.error(String.format("Task with ID %s not found", taskId));
                throw new NotFoundException(String.format("Task with ID %s not found", taskId));
            }
            return getById(taskId);
        } catch (DataIntegrityViolationException e) {
            log.error(String.format("Referential integrity violated for user with ID %s", userId));
            throw new BadRequestException(String.format("Referential integrity violated for user with ID %s", userId));
        }
    }

    @Transactional
    @Override
    public TaskResponseDto assignRelease(Long taskId, Long releaseId) {
        try {
            int count = taskRepository.assignRelease(taskId, releaseId);
            if (count == 0) {
                log.error(String.format("Task with ID %s not found", taskId));
                throw new NotFoundException(String.format("Task with ID %s not found", taskId));
            }
            return getById(taskId);
        } catch (DataIntegrityViolationException e) {
            log.error(String.format("Referential integrity violated for release with ID %s", releaseId));
            throw new BadRequestException(String.format("Referential integrity violated for release with ID %s", releaseId));
        }
    }

    @Transactional
    @Override
    public TaskResponseDto complete(Long id) {
        int count = taskRepository.complete(id);
        if (count == 0) {
            log.error(String.format("Task with ID %s not found", id));
            throw new NotFoundException(String.format("Task with ID %s not found", id));
        }
        return getById(id);
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDto> filterTask(TaskFilterDTO taskFilter) {
        TaskSpecification specification = new TaskSpecification(taskFilter);
        return taskRepository.findAll(specification).stream()
                .map(taskMapper::taskToTaskResponseDto)
                .collect(Collectors.toList());
    }
}
