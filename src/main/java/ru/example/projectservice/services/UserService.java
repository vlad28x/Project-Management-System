package ru.example.projectservice.services;

import ru.example.projectservice.dto.UserRequestDto;
import ru.example.projectservice.dto.UserResponseDto;
import ru.example.projectservice.entities.User;

import java.util.List;

/**
 * Service for user
 */
public interface UserService {

    /**
     * Get all users.
     *
     * @return list of UserResponseDto
     */
    List<UserResponseDto> getAll();

    /**
     * Get specific user.
     *
     * @param id - identifier of user
     * @return UserResponseDto
     */
    UserResponseDto getById(Long id);

    /**
     * Add user.
     *
     * @param newUser - UserRequestDto
     * @return UserResponseDto
     */
    UserResponseDto add(UserRequestDto newUser);

    /**
     * Update user.
     *
     * @param newUser - UserRequestDto
     * @return UserResponseDto
     */
    UserResponseDto update(UserRequestDto newUser);

    /**
     * Delete specific user.
     *
     * @param id - identifier of user
     */
    void delete(Long id);

    /**
     * Find User by username
     *
     * @param username - username of User
     * @return User entity
     */
    User findByUsername(String username);

}
