package ru.example.projectmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.example.projectmanagement.entities.Project;

/**
 * Specific extension of JpaRepository for Project.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    /**
     * Complete a specific project.
     *
     * @param id - identifier of project
     * @return the number of changed entities
     */
    @Modifying
    @Query("update Project p set p.status = ru.example.projectmanagement.entities.enums.Status.DONE where p.id = :id")
    int complete(Long id);
}
