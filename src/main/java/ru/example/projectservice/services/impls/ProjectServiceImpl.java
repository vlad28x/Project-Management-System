package ru.example.projectservice.services.impls;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.projectservice.dto.DebtRequestDto;
import ru.example.projectservice.dto.DebtResponseDto;
import ru.example.projectservice.dto.ProjectRequestDto;
import ru.example.projectservice.dto.ProjectResponseDto;
import ru.example.projectservice.entities.Project;
import ru.example.projectservice.entities.enums.ProjectStatus;
import ru.example.projectservice.entities.enums.TaskStatus;
import ru.example.projectservice.exceptions.BadRequestException;
import ru.example.projectservice.exceptions.NotFoundException;
import ru.example.projectservice.proxy.PaymentClient;
import ru.example.projectservice.repositories.ProjectRepository;
import ru.example.projectservice.repositories.TaskRepository;
import ru.example.projectservice.repositories.UserRepository;
import ru.example.projectservice.security.JwtTokenProvider;
import ru.example.projectservice.services.ProjectService;
import ru.example.projectservice.utils.mappers.ProjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);
    private static final ProjectMapper projectMapper = Mappers.getMapper(ProjectMapper.class);

    private final JwtTokenProvider jwtTokenProvider;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final PaymentClient paymentClient;

    public ProjectServiceImpl(JwtTokenProvider jwtTokenProvider, ProjectRepository projectRepository, TaskRepository taskRepository, PaymentClient paymentClient) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.paymentClient = paymentClient;
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
        Long countUnderdoneTasks = taskRepository.countByProjectIdAndStatusNot(id, TaskStatus.DONE);
        if (countUnderdoneTasks == 0) {
            projectRepository.complete(id);
            return getById(id);
        }
        log.error(String.format("Project with ID %s has %s underdone tasks", id, countUnderdoneTasks));
        throw new BadRequestException(String.format("Project with ID %s has %s underdone tasks", id, countUnderdoneTasks));
    }

    @Override
    public ProjectResponseDto pay(String jwt) {
        String token = null;
        if (jwt != null && jwt.startsWith("Bearer ")) {
            token = jwt.substring(7);
        }
        String username = jwtTokenProvider.getUsername(token);
        Project project = projectRepository.findByCustomerUsername(username);
        if(project == null) throw new BadRequestException("Customer doesn't have a project");
        DebtRequestDto debtRequest = new DebtRequestDto();
        debtRequest.setDebt(project.getDebt());
        Integer debt = paymentClient.payDebt(jwt, debtRequest).getBody().getDebt();
        if(debt != null) {
            project.setDebt(debt);
            if(debt == 0) {
                project.setStatus(ProjectStatus.IN_PROGRESS);
            }
            projectRepository.save(project);
        }
        return projectMapper.projectToProjectResponseDto(project);
    }

}
