package ru.example.projectmanagement.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.UserRequestDto;
import ru.example.projectmanagement.dto.UserResponseDto;
import ru.example.projectmanagement.entities.User;
import ru.example.projectmanagement.services.UserService;
import ru.example.projectmanagement.utils.mappers.UserMapper;

import java.util.List;
import java.util.stream.Collectors;


@Tag(name = "Пользователи", description = "Взаимодействие с пользователями")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Получение всех пользователей", description = "Позволяет получить всех пользователей")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> list = userService.getAll().stream()
                .map(userMapper::userToUserResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PreAuthorize("hasRole('PROGRAMMER')")
    @Operation(summary = "Получение одного пользователя",
            description = "Позволяет получить одного пользователя по заданному ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(userMapper.userToUserResponseDto(user));
    }

    @PreAuthorize("hasRole('PROGRAMMER')")
    @Operation(summary = "Добавление пользователя", description = "Позволяет добавить пользователя")
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto newUser) {
        User user = userMapper.userRequestDtoToUser(newUser);
        user = userService.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userToUserResponseDto(user));
    }

    @PreAuthorize("hasRole('PROGRAMMER')")
    @Operation(summary = "Обновление пользователя", description = "Позволяет обновить пользователя по заданному ID")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto newUser) {
        User user = userMapper.userRequestDtoToUser(newUser);
        user = userService.add(user);
        return ResponseEntity.ok(userMapper.userToUserResponseDto(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Удаление пользователя", description = "Позволяет удалить пользователя по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
