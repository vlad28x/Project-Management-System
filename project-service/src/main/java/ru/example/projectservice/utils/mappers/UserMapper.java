package ru.example.projectservice.utils.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.example.projectservice.dto.UserRequestDto;
import ru.example.projectservice.dto.UserResponseDto;
import ru.example.projectservice.entities.User;

@Mapper
public abstract class UserMapper {

    @Mapping(source = "project.id", target = "projectId")
    public abstract UserResponseDto userToUserResponseDto(User user);

    @Mapping(source = "projectId", target = "project.id")
    public abstract User userRequestDtoToUser(UserRequestDto user);

    @AfterMapping
    protected void setNullProjectIfProjectIdIsNull(@MappingTarget User user, UserRequestDto userRequestDto) {
        if (userRequestDto.getProjectId() == null) user.setProject(null);
    }
}
