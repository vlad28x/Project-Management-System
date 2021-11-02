package ru.example.projectmanagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.UserRequestDTO;
import ru.example.projectmanagement.dto.UserResponseDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> list = new ArrayList<>();
        list.add(new UserResponseDTO());
        return list;
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return new UserResponseDTO();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@RequestBody UserRequestDTO newUser) {
        return new UserResponseDTO();
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @RequestBody UserRequestDTO newUser) {
        return new UserResponseDTO();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
    }
}
