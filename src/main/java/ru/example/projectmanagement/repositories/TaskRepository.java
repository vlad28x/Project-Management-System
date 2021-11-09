package ru.example.projectmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.projectmanagement.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
