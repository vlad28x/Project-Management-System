package ru.example.projectmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.projectmanagement.entities.User;

import java.util.Optional;

/**
 * Specific extension of JpaRepository for User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by username.
     *
     * @param username - user username
     * @return Optional of User
     */
    Optional<User> findByUsername(String username);
}
