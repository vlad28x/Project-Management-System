package ru.example.projectmanagement.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.example.projectmanagement.dto.UserRequestDto;
import ru.example.projectmanagement.dto.UserResponseDto;
import ru.example.projectmanagement.entities.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "project.id", target = "projectId")
    UserResponseDto userToUserResponseDto(User user);

    @Mapping(source = "projectId", target = "project.id")
    User userRequestDtoToUser(UserRequestDto user);
}
