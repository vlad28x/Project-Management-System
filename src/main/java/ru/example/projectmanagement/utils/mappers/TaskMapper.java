package ru.example.projectmanagement.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.example.projectmanagement.dto.TaskRequestDto;
import ru.example.projectmanagement.dto.TaskResponseDto;
import ru.example.projectmanagement.entities.Task;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskResponseDto taskToTaskResponseDto(Task task);

    @Mappings({
            @Mapping(source = "assignerId", target = "assigner.id"),
            @Mapping(source = "ownerId", target = "owner.id"),
            @Mapping(source = "projectId", target = "project.id"),
            @Mapping(source = "releaseId", target = "release.id")
    })
    Task taskRequestDtoToTask(TaskRequestDto task);
}
