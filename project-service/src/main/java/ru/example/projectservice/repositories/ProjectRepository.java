package ru.example.projectservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.example.projectservice.entities.Project;

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
    @Query("update Project p set p.status = ru.example.projectservice.entities.enums.ProjectStatus.DONE where p.id = :id")
    int complete(Long id);
}
