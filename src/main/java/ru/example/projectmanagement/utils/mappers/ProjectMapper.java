package ru.example.projectmanagement.utils.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.example.projectmanagement.dto.ProjectRequestDto;
import ru.example.projectmanagement.dto.ProjectResponseDto;
import ru.example.projectmanagement.entities.Project;

@Mapper
public abstract class ProjectMapper {

    @Mapping(source = "owner.id", target = "ownerId")
    public abstract ProjectResponseDto projectToProjectResponseDto(Project project);

    @Mapping(source = "ownerId", target = "owner.id")
    public abstract Project projectRequestDtoToProject(ProjectRequestDto project);

    @AfterMapping
    protected void setNullOwnerIfOwnerIdNull(@MappingTarget Project project, ProjectRequestDto projectRequestDto) {
        if (projectRequestDto.getOwnerId() == null) project.setOwner(null);
    }
}
