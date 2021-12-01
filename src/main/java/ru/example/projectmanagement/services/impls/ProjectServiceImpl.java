package ru.example.projectmanagement.services.impls;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.projectmanagement.dto.ProjectRequestDto;
import ru.example.projectmanagement.dto.ProjectResponseDto;
import ru.example.projectmanagement.entities.Project;
import ru.example.projectmanagement.entities.enums.Status;
import ru.example.projectmanagement.exceptions.BadRequestException;
import ru.example.projectmanagement.exceptions.NotFoundException;
import ru.example.projectmanagement.repositories.ProjectRepository;
import ru.example.projectmanagement.repositories.TaskRepository;
import ru.example.projectmanagement.services.ProjectService;
import ru.example.projectmanagement.utils.mappers.ProjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);
    private static final ProjectMapper projectMapper = Mappers.getMapper(ProjectMapper.class);

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProjectResponseDto> getAll() {
        return projectRepository.findAll().stream()
                .map(projectMapper::projectToProjectResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ProjectResponseDto getById(Long id) {
        return projectMapper.projectToProjectResponseDto(projectRepository.findById(id).orElseThrow(() -> {
            log.error(String.format("Project with ID %s not found", id));
            return new NotFoundException(String.format("Project with ID %s not found", id));
        }));
    }

    @Override
    public ProjectResponseDto add(ProjectRequestDto newProject) {
        try {
            return projectMapper.projectToProjectResponseDto(projectRepository.save(
                    projectMapper.projectRequestDtoToProject(newProject))
            );
        } catch (NestedRuntimeException e) {
            log.error(e.getMessage(), e.getCause());
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public ProjectResponseDto update(ProjectRequestDto newProject) {
        try {
            return projectMapper.projectToProjectResponseDto(projectRepository.save(
                    projectMapper.projectRequestDtoToProject(newProject))
            );
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
    public ProjectResponseDto complete(Long id) {
        Long countUnderdoneTasks = taskRepository.countByProjectIdAndStatusNot(id, Status.DONE);
        if (countUnderdoneTasks == 0) {
            projectRepository.complete(id);
            return getById(id);
        }
        log.error(String.format("Project with ID %s has %s underdone tasks", id, countUnderdoneTasks));
        throw new BadRequestException(String.format("Project with ID %s has %s underdone tasks", id, countUnderdoneTasks));
    }

}
