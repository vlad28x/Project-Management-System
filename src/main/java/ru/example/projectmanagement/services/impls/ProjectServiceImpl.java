package ru.example.projectmanagement.services.impls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.projectmanagement.entities.Project;
import ru.example.projectmanagement.entities.enums.Status;
import ru.example.projectmanagement.exceptions.BadRequestException;
import ru.example.projectmanagement.exceptions.NotFoundException;
import ru.example.projectmanagement.repositories.ProjectRepository;
import ru.example.projectmanagement.repositories.TaskRepository;
import ru.example.projectmanagement.services.ProjectService;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Project getById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> {
            log.error(String.format("Project with ID %s not found", id));
            return new NotFoundException(String.format("Project with ID %s not found", id));
        });
    }

    @Override
    public Project add(Project project) {
        try {
            return projectRepository.save(project);
        } catch (NestedRuntimeException e) {
            log.error(e.getMessage(), e.getCause());
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public Project update(Project project) {
        try {
            return projectRepository.save(project);
        } catch (NestedRuntimeException e) {
            log.error(e.getMessage(), e.getCause());
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Project complete(Long id) {
        // Количество невыполненных задач в проекте
        Long countUnderdoneTasks = taskRepository.countByProjectIdAndStatusNot(id, Status.DONE);
        if (countUnderdoneTasks == 0) {
            projectRepository.complete(id);
            return getById(id);
        }
        log.error(String.format("Project with ID %s has %s underdone tasks", id, countUnderdoneTasks));
        throw new BadRequestException(String.format("Project with ID %s has %s underdone tasks", id, countUnderdoneTasks));
    }

}
