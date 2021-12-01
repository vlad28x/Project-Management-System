package ru.example.projectmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.projectmanagement.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
