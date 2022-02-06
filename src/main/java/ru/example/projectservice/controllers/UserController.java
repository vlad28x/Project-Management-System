package ru.example.projectservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.example.projectservice.dto.UserRequestDto;
import ru.example.projectservice.dto.UserResponseDto;
import ru.example.projectservice.services.UserService;

import java.util.List;


@Tag(name = "Пользователи", description = "Взаимодействие с пользователями")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Получение всех пользователей", description = "Позволяет получить всех пользователей")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PreAuthorize("hasRole('PROGRAMMER')")
    @Operation(summary = "Получение одного пользователя",
            description = "Позволяет получить одного пользователя по заданному ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PreAuthorize("permitAll()")
    @Operation(summary = "Добавление пользователя", description = "Позволяет добавить пользователя")
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto newUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.add(newUser));
    }

    @PreAuthorize("hasRole('PROGRAMMER')")
    @Operation(summary = "Обновление пользователя", description = "Позволяет обновить пользователя по заданному ID")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto newUser) {
        userService.getById(id);
        newUser.setId(id);
        return ResponseEntity.ok(userService.update(newUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Удаление пользователя", description = "Позволяет удалить пользователя по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
