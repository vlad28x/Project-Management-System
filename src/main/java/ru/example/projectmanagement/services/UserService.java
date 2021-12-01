package ru.example.projectmanagement.services;

import ru.example.projectmanagement.entities.User;

import java.util.List;

/**
 * Service for user
 */
public interface UserService {

    /**
     * Get all users.
     *
     * @return list of users
     */
    List<User> getAll();

    /**
     * Get specific user.
     *
     * @param id - identifier of user
     * @return user entity
     */
    User getById(Long id);

    /**
     * Add user.
     *
     * @param user - user entity
     * @return user entity
     */
    User add(User user);

    /**
     * Update user.
     *
     * @param user - user entity
     * @return user entity
     */
    User update(User user);

    /**
     * Delete specific user.
     *
     * @param id - identifier of user
     */
    void delete(Long id);

}
