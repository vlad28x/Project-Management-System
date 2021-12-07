package ru.example.projectservice.services.impls;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import ru.example.projectservice.dto.DebtResponseDto;
import ru.example.projectservice.dto.ProjectRequestDto;
import ru.example.projectservice.dto.ProjectResponseDto;
import ru.example.projectservice.entities.Project;
import ru.example.projectservice.entities.User;
import ru.example.projectservice.entities.enums.ProjectStatus;
import ru.example.projectservice.entities.enums.TaskStatus;
import ru.example.projectservice.exceptions.BadRequestException;
import ru.example.projectservice.exceptions.NotFoundException;
import ru.example.projectservice.proxy.PaymentClient;
import ru.example.projectservice.repositories.ProjectRepository;
import ru.example.projectservice.repositories.TaskRepository;
import ru.example.projectservice.security.JwtTokenProvider;
import ru.example.projectservice.utils.mappers.ProjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplMockTest {

    private static final ProjectMapper projectMapper = Mappers.getMapper(ProjectMapper.class);

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private PaymentClient paymentClient;

    @Test
    void getAllProjectsTest() {
        List<Project> list = new ArrayList<>(Arrays.asList(new Project(), new Project(), new Project()));
        Mockito.when(projectRepository.findAll()).thenReturn(list);
        List<ProjectResponseDto> excepted = projectService.getAll();
        assertEquals(excepted.size(), list.size());
    }

    @Test
    void getProjectByIdTest() {
        User owner = new User();
        owner.setId(2L);
        User customer = new User();
        customer.setId(8L);
        Project project = new Project();
        project.setId(1L);
        project.setName("Система управления проектами");
        project.setDescription("Проект написан с использование Spring Boot");
        project.setStartDate(LocalDate.of(2021, 10, 25));
        project.setEndDate(LocalDate.of(2021, 12, 15));
        project.setDebt(100000);
        project.setStatus(ProjectStatus.PAYMENT);
        project.setOwner(owner);
        project.setCustomer(customer);

        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        ProjectResponseDto actual = projectService.getById(1L);
        ProjectResponseDto expected = projectMapper.projectToProjectResponseDto(project);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getStartDate(), actual.getStartDate());
        assertEquals(expected.getEndDate(), actual.getEndDate());
        assertEquals(expected.getDebt(), actual.getDebt());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getOwnerId(), actual.getOwnerId());
        assertEquals(expected.getCustomerId(), actual.getCustomerId());
    }

    @Test
    void getProjectByIdFailTest() {
        Mockito.when(projectRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> projectService.getById(1L));
    }

    @Test
    void addProjectTest() {
        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setId(1L);
        projectRequestDto.setName("Система управления проектами");
        projectRequestDto.setDescription("Проект написан с использование Spring Boot");
        projectRequestDto.setStartDate(LocalDate.of(2021, 10, 25));
        projectRequestDto.setEndDate(LocalDate.of(2021, 12, 15));
        projectRequestDto.setDebt(100000);
        projectRequestDto.setStatus(ProjectStatus.PAYMENT);
        projectRequestDto.setOwnerId(2L);
        projectRequestDto.setCustomerId(8L);

        Project project = projectMapper.projectRequestDtoToProject(projectRequestDto);
        Mockito.when(projectRepository.save(Mockito.any(Project.class))).thenReturn(project);

        ProjectResponseDto actual = projectService.add(projectRequestDto);
        ProjectResponseDto expected = projectMapper.projectToProjectResponseDto(project);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getStartDate(), actual.getStartDate());
        assertEquals(expected.getEndDate(), actual.getEndDate());
        assertEquals(expected.getDebt(), actual.getDebt());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getOwnerId(), actual.getOwnerId());
        assertEquals(expected.getCustomerId(), actual.getCustomerId());
    }

    @Test
    void addProjectFailTest() {
        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        Mockito.when(projectRepository.save(Mockito.any(Project.class))).thenThrow(BadRequestException.class);
        assertThrows(BadRequestException.class, () -> projectService.add(projectRequestDto));
    }

    @Test
    void addProjectNullTest() {
        Mockito.when(projectRepository.save(Mockito.isNull())).thenThrow(BadRequestException.class);
        assertThrows(BadRequestException.class, () -> projectService.add(null));
    }

    @Test
    void updateProjectTest() {
        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setId(1L);
        projectRequestDto.setName("Система управления проектами");
        projectRequestDto.setDescription("Проект написан с использование Spring Boot");
        projectRequestDto.setStartDate(LocalDate.of(2021, 10, 25));
        projectRequestDto.setEndDate(LocalDate.of(2021, 12, 15));
        projectRequestDto.setDebt(100000);
        projectRequestDto.setStatus(ProjectStatus.PAYMENT);
        projectRequestDto.setOwnerId(2L);
        projectRequestDto.setCustomerId(8L);

        Project project = projectMapper.projectRequestDtoToProject(projectRequestDto);
        Mockito.when(projectRepository.save(Mockito.any(Project.class))).thenReturn(project);
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        ProjectResponseDto actual = projectService.update(projectRequestDto);
        ProjectResponseDto expected = projectMapper.projectToProjectResponseDto(project);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getStartDate(), actual.getStartDate());
        assertEquals(expected.getEndDate(), actual.getEndDate());
        assertEquals(expected.getDebt(), actual.getDebt());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getOwnerId(), actual.getOwnerId());
        assertEquals(expected.getCustomerId(), actual.getCustomerId());
    }

    @Test
    void updateProjectFailTest() {
        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setId(1L);
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> projectService.update(projectRequestDto));
    }

    @Test
    void deleteProjectTest() {
        projectService.delete(1L);
        Mockito.verify(projectRepository, Mockito.times(1)).deleteById(Mockito.any(Long.class));
    }

    @Test
    void completeProjectTest() {
        Project project = new Project();
        project.setStatus(ProjectStatus.DONE);
        Mockito.when(taskRepository.countByProjectIdAndStatusNot(Mockito.any(Long.class), Mockito.eq(TaskStatus.DONE))).thenReturn(0L);
        Mockito.when(projectRepository.complete(Mockito.any(Long.class))).thenReturn(1);
        Mockito.when(projectRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(project));
        ProjectResponseDto actual = projectService.complete(1L);
        assertEquals(ProjectStatus.DONE, actual.getStatus());
    }

    @Test
    void payProjectTest() {
        DebtResponseDto debtResponseDto = new DebtResponseDto();
        debtResponseDto.setDebt(0);
        Project project = new Project();
        project.setDebt(100000);
        project.setStatus(ProjectStatus.PAYMENT);
        Mockito.when(jwtTokenProvider.getUsername(Mockito.any(String.class))).thenReturn("vlad28x");
        Mockito.when(projectRepository.findByCustomerUsername("vlad28x")).thenReturn(project);
        Mockito.when(paymentClient.payDebt(Mockito.any(String.class), Mockito.any())).thenReturn(ResponseEntity.ok(debtResponseDto));
        ProjectResponseDto actual = projectService.pay("Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjIiLCJyb2xlIjoiQ1VTVE9NRVIiLCJpYXQiOjE2MzgzOTExMjMsImV4cCI6MTYzODQ3ODgwMH0.HzVvEZHVWnR3ITjRpYppEJJHAyXVma_Rkzx1WCHW-6g");
        Mockito.verify(projectRepository, Mockito.times(1)).save(Mockito.any(Project.class));
        assertEquals(ProjectStatus.IN_PROGRESS, actual.getStatus());
    }

}