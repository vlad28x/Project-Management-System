package ru.example.projectmanagement.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.UserRequestDto;
import ru.example.projectmanagement.dto.UserResponseDto;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Пользователи", description = "Взаимодействие с пользователями")
@RestController
@RequestMapping("/users")
public class UserController {

    @Operation(summary = "Получение всех пользователей", description = "Позволяет получить всех пользователей")
    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        List<UserResponseDto> list = new ArrayList<>();
        list.add(new UserResponseDto());
        return list;
    }

    @Operation(summary = "Получение одного пользователя",
            description = "Позволяет получить одного пользователя по заданному ID")
    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return new UserResponseDto();
    }

    @Operation(summary = "Добавление пользователя", description = "Позволяет добавить пользователя")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(@RequestBody UserRequestDto newUser) {
        return new UserResponseDto();
    }

    @Operation(summary = "Обновление пользователя", description = "Позволяет обновить пользователя по заданному ID")
    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserRequestDto newUser) {
        return new UserResponseDto();
    }

    @Operation(summary = "Удаление пользователя", description = "Позволяет удалить пользователя по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
    }
}
