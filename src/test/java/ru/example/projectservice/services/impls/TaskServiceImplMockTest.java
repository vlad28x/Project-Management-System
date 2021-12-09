package ru.example.projectservice.services.impls;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.example.projectservice.dto.TaskFilterDTO;
import ru.example.projectservice.dto.TaskRequestDto;
import ru.example.projectservice.dto.TaskResponseDto;
import ru.example.projectservice.entities.Project;
import ru.example.projectservice.entities.Task;
import ru.example.projectservice.entities.User;
import ru.example.projectservice.entities.enums.TaskStatus;
import ru.example.projectservice.entities.enums.Type;
import ru.example.projectservice.exceptions.BadRequestException;
import ru.example.projectservice.exceptions.NotFoundException;
import ru.example.projectservice.repositories.TaskRepository;
import ru.example.projectservice.services.specifications.Operation;
import ru.example.projectservice.services.specifications.TaskSpecification;
import ru.example.projectservice.utils.mappers.TaskMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplMockTest {

    private static final TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);
    private static final LocalDateTime LOCAL_DATE_TIME_NOW = LocalDateTime.now();

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    void getAllTasksTest() {
        List<Task> list = new ArrayList<>(Arrays.asList(new Task(), new Task(), new Task()));
        Mockito.when(taskRepository.findAll()).thenReturn(list);
        List<TaskResponseDto> excepted = taskService.getAll();
        assertEquals(excepted.size(), list.size());
    }

    @Test
    void getTaskByIdSuccessTest() {
        User owner = new User();
        owner.setId(2L);
        Project project = new Project();
        project.setId(1L);
        Task task = new Task();
        task.setId(1L);
        task.setName("TASK-1: Создать БД");
        task.setDescription("Создать базу данных в СУБД PostgreSQL");
        task.setCreatedAt(LOCAL_DATE_TIME_NOW);
        task.setUpdatedAt(LOCAL_DATE_TIME_NOW);
        task.setStatus(TaskStatus.BACKLOG);
        task.setType(Type.FEATURE);
        task.setOwner(owner);
        task.setProject(project);

        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        TaskResponseDto actual = taskService.getById(1L);
        TaskResponseDto expected = taskMapper.taskToTaskResponseDto(task);

        assertEqualsTaskResponseDto(expected, actual);
    }

    @Test
    void getTaskByIdFailTest() {
        Mockito.when(taskRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> taskService.getById(1L));
    }

    @Test
    void addTaskSuccessTest() {
        TaskRequestDto taskRequestDto = new TaskRequestDto();
        taskRequestDto.setId(1L);
        taskRequestDto.setName("TASK-1: Создать БД");
        taskRequestDto.setDescription("Создать базу данных в СУБД PostgreSQL");
        taskRequestDto.setCreatedAt(LOCAL_DATE_TIME_NOW);
        taskRequestDto.setUpdatedAt(LOCAL_DATE_TIME_NOW);
        taskRequestDto.setStatus(TaskStatus.BACKLOG);
        taskRequestDto.setType(Type.FEATURE);
        taskRequestDto.setOwnerId(2L);
        taskRequestDto.setProjectId(1L);
        taskRequestDto.setReleaseId(1L);

        Task task = taskMapper.taskRequestDtoToTask(taskRequestDto);
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);

        TaskResponseDto actual = taskService.add(taskRequestDto);
        TaskResponseDto expected = taskMapper.taskToTaskResponseDto(task);

        assertEqualsTaskResponseDto(expected, actual);
    }

    @Test
    void addTaskFailTest() {
        TaskRequestDto taskRequestDto = new TaskRequestDto();
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenThrow(BadRequestException.class);
        assertThrows(BadRequestException.class, () -> taskService.add(taskRequestDto));
    }

    @Test
    void updateTaskSuccessTest() {
        TaskRequestDto taskRequestDto = new TaskRequestDto();
        taskRequestDto.setId(1L);
        taskRequestDto.setName("TASK-1: Создать БД");
        taskRequestDto.setDescription("Создать базу данных в СУБД PostgreSQL");
        taskRequestDto.setCreatedAt(LOCAL_DATE_TIME_NOW);
        taskRequestDto.setUpdatedAt(LOCAL_DATE_TIME_NOW);
        taskRequestDto.setStatus(TaskStatus.BACKLOG);
        taskRequestDto.setType(Type.FEATURE);
        taskRequestDto.setOwnerId(2L);
        taskRequestDto.setProjectId(1L);
        taskRequestDto.setReleaseId(1L);

        Task task = taskMapper.taskRequestDtoToTask(taskRequestDto);
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);
        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        TaskResponseDto actual = taskService.update(taskRequestDto);
        TaskResponseDto expected = taskMapper.taskToTaskResponseDto(task);

        assertEqualsTaskResponseDto(expected, actual);
    }

    @Test
    void updateTaskFailTest() {
        TaskRequestDto taskRequestDto = new TaskRequestDto();
        taskRequestDto.setId(1L);
        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> taskService.update(taskRequestDto));
    }

    @Test
    void deleteTaskSuccessTest() {
        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(new Task()));
        taskService.delete(1L);
        Mockito.verify(taskRepository, Mockito.times(1)).deleteById(Mockito.any(Long.class));
    }

    @Test
    void deleteTaskFailTest() {
        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> taskService.delete(1L));
        Mockito.verify(taskRepository, Mockito.times(0)).deleteById(Mockito.any(Long.class));
    }

    @Test
    void assignUserSuccessTest() {
        Long taskId = 1L;
        Long assignerId = 2L;
        Mockito.when(taskRepository.assignUser(taskId, assignerId)).thenReturn(1);
        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(new Task()));
        taskService.assignUser(taskId, assignerId);
        Mockito.verify(taskRepository, Mockito.times(1)).findById(taskId);
        Mockito.verify(taskRepository, Mockito.times(1)).assignUser(taskId, assignerId);
    }

    @Test
    void assignUserFailTest() {
        Long taskId = 1L;
        Long assignerId = 2L;
        Mockito.when(taskRepository.assignUser(taskId, assignerId)).thenReturn(0);
        assertThrows(NotFoundException.class, () -> taskService.assignUser(taskId, assignerId));
    }

    @Test
    void assignReleaseSuccessTest() {
        Long taskId = 1L;
        Long releaseId = 2L;
        Mockito.when(taskRepository.assignRelease(taskId, releaseId)).thenReturn(1);
        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(new Task()));
        taskService.assignRelease(taskId, releaseId);
        Mockito.verify(taskRepository, Mockito.times(1)).findById(taskId);
        Mockito.verify(taskRepository, Mockito.times(1)).assignRelease(taskId, releaseId);
    }

    @Test
    void assignReleaseFailTest() {
        Long taskId = 1L;
        Long releaseId = 2L;
        Mockito.when(taskRepository.assignRelease(taskId, releaseId)).thenReturn(0);
        assertThrows(NotFoundException.class, () -> taskService.assignRelease(taskId, releaseId));
    }

    @Test
    void completeTaskSuccessId() {
        Long taskId = 1L;
        Mockito.when(taskRepository.complete(taskId)).thenReturn(1);
        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(new Task()));
        taskService.complete(taskId);
        Mockito.verify(taskRepository, Mockito.times(1)).findById(taskId);
        Mockito.verify(taskRepository, Mockito.times(1)).complete(taskId);
    }

    @Test
    void completeTaskFailId() {
        Long taskId = 1L;
        Mockito.when(taskRepository.complete(taskId)).thenReturn(0);
        assertThrows(NotFoundException.class, () -> taskService.complete(taskId));
    }

    @Test
    void filterTaskTest() {
        List<Task> list = new ArrayList<>(Arrays.asList(new Task(), new Task(), new Task()));
        TaskFilterDTO taskFilterDTO = new TaskFilterDTO();
        taskFilterDTO.setKey("name");
        taskFilterDTO.setOperation(Operation.LIKE);
        taskFilterDTO.setValue("TASK-1");
        Mockito.when(taskRepository.findAll(Mockito.any(TaskSpecification.class))).thenReturn(list);
        List<TaskResponseDto> actual = taskService.filterTask(taskFilterDTO);
        assertEquals(list.size(), actual.size());
    }

    private void assertEqualsTaskResponseDto(TaskResponseDto expected, TaskResponseDto actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getStartDate(), actual.getStartDate());
        assertEquals(expected.getEndDate(), actual.getEndDate());
        assertEquals(expected.getReleaseId(), actual.getReleaseId());
        assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
        assertEquals(expected.getUpdatedAt(), actual.getUpdatedAt());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getOwnerId(), actual.getOwnerId());
        assertEquals(expected.getProjectId(), actual.getProjectId());
    }

}