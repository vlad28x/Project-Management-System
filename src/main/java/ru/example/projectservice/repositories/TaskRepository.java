package ru.example.projectservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.example.projectservice.entities.Task;
import ru.example.projectservice.entities.enums.TaskStatus;

/**
 * Specific extension of JpaRepository for Task.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    /**
     * Count tasks by identifier of project and not specific status of task.
     *
     * @param projectId - identifier of project
     * @param status    - status of task
     * @return the number of tasks
     */
    Long countByProjectIdAndStatusNot(Long projectId, TaskStatus status);

    /**
     * Assign a task to a specific user.
     *
     * @param taskId - identifier of task
     * @param userId - identifier of user
     * @return the number of changed entities
     */
    @Modifying
    @Query("update Task t set t.assigner.id = :userId, " +
            "t.status = ru.example.projectservice.entities.enums.TaskStatus.IN_PROGRESS " +
            "where t.id = :taskId")
    int assignUser(Long taskId, Long userId);

    /**
     * Assign a task to a specific release.
     *
     * @param taskId    - identifier of task
     * @param releaseId - identifier of release
     * @return the number of changed entities
     */
    @Modifying
    @Query("update Task t set t.release.id = :releaseId where t.id = :taskId")
    int assignRelease(Long taskId, Long releaseId);

    /**
     * Complete a specific task.
     *
     * @param id - identifier of task
     * @return the number of changed entities
     */
    @Modifying
    @Query("update Task t set t.status = ru.example.projectservice.entities.enums.TaskStatus.DONE where t.id = :id")
    int complete(Long id);
}
