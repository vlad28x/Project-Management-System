package ru.example.projectmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.projectmanagement.entities.Release;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {
}
