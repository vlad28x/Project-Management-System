package ru.example.projectmanagement.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.example.projectmanagement.dto.ProjectRequestDto;
import ru.example.projectmanagement.dto.ProjectResponseDto;
import ru.example.projectmanagement.entities.Project;

@Mapper
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(source = "owner.id", target = "ownerId")
    ProjectResponseDto projectToProjectResponseDto(Project project);

    @Mapping(source = "ownerId", target = "owner.id")
    Project projectRequestDtoToProject(ProjectRequestDto project);
}
