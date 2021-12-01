package ru.example.projectmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.projectmanagement.entities.Release;
import ru.example.projectmanagement.entities.enums.Status;

/**
 * Specific extension of JpaRepository for Release.
 */
@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {

    /**
     * Count tasks by identifier of release and not specific status of task.
     *
     * @param id     - identifier of release
     * @param status - status of tasks
     * @return the number of tasks
     */
    Long countByIdAndTasksStatusNot(Long id, Status status);
}
