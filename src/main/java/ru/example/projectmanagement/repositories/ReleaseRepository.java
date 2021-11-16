package ru.example.projectmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.projectmanagement.entities.Release;
import ru.example.projectmanagement.entities.enums.Status;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {

    Long countByIdAndTasksStatusNot(Long id, Status status);
}
