package ru.example.projectmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.projectmanagement.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
