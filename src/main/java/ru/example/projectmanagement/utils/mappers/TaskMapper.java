package ru.example.projectmanagement.utils.mappers;

import org.mapstruct.*;
import ru.example.projectmanagement.dto.TaskRequestDto;
import ru.example.projectmanagement.dto.TaskResponseDto;
import ru.example.projectmanagement.entities.Task;

@Mapper
public abstract class TaskMapper {

    @Mappings({
            @Mapping(source = "assigner.id", target = "assignerId"),
            @Mapping(source = "owner.id", target = "ownerId"),
            @Mapping(source = "project.id", target = "projectId"),
            @Mapping(source = "release.id", target = "releaseId")
    })
    public abstract TaskResponseDto taskToTaskResponseDto(Task task);

    @Mappings({
            @Mapping(source = "assignerId", target = "assigner.id"),
            @Mapping(source = "ownerId", target = "owner.id"),
            @Mapping(source = "projectId", target = "project.id"),
            @Mapping(source = "releaseId", target = "release.id")
    })
    public abstract Task taskRequestDtoToTask(TaskRequestDto task);

    @AfterMapping
    protected void setNullOwnerIfOwnerIdIsNull(@MappingTarget Task task, TaskRequestDto taskRequestDto) {
        if (taskRequestDto.getOwnerId() == null) task.setOwner(null);
    }

    @AfterMapping
    protected void setNullProjectIfProjectIdIsNull(@MappingTarget Task task, TaskRequestDto taskRequestDto) {
        if (taskRequestDto.getProjectId() == null) task.setProject(null);
    }

    @AfterMapping
    protected void setNullReleaseIfReleaseIdIsNull(@MappingTarget Task task, TaskRequestDto taskRequestDto) {
        if (taskRequestDto.getReleaseId() == null) task.setRelease(null);
    }

    @AfterMapping
    protected void setNullAssignerIfAssignerIdIsNull(@MappingTarget Task task, TaskRequestDto taskRequestDto) {
        if (taskRequestDto.getAssignerId() == null) task.setAssigner(null);
    }
}
