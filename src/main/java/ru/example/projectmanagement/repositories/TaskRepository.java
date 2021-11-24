package ru.example.projectmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.example.projectmanagement.entities.Task;
import ru.example.projectmanagement.entities.enums.Status;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    Long countByProjectIdAndStatusNot(Long projectId, Status status);

    @Modifying
    @Query("update Task t set t.assigner.id = :userId, " +
            "t.status = ru.example.projectmanagement.entities.enums.Status.IN_PROGRESS " +
            "where t.id = :taskId")
    int assignUser(Long taskId, Long userId);

    @Modifying
    @Query("update Task t set t.release.id = :releaseId where t.id = :taskId")
    int assignRelease(Long taskId, Long releaseId);

    @Modifying
    @Query("update Task t set t.status = ru.example.projectmanagement.entities.enums.Status.DONE where t.id = :id")
    int complete(Long id);
}
