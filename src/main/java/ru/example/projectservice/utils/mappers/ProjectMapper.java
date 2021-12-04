package ru.example.projectservice.utils.mappers;

import org.mapstruct.*;
import ru.example.projectservice.dto.ProjectRequestDto;
import ru.example.projectservice.dto.ProjectResponseDto;
import ru.example.projectservice.entities.Project;

@Mapper
public abstract class ProjectMapper {

    @Mappings({
            @Mapping(source = "owner.id", target = "ownerId"),
            @Mapping(source = "customer.id", target = "customerId")
    })
    public abstract ProjectResponseDto projectToProjectResponseDto(Project project);

    @Mappings({
            @Mapping(source = "ownerId", target = "owner.id"),
            @Mapping(source = "customerId", target = "customer.id"),
    })
    public abstract Project projectRequestDtoToProject(ProjectRequestDto project);

    @AfterMapping
    protected void setNullOwnerIfOwnerIdNull(@MappingTarget Project project, ProjectRequestDto projectRequestDto) {
        if (projectRequestDto.getOwnerId() == null) project.setOwner(null);
    }
}
