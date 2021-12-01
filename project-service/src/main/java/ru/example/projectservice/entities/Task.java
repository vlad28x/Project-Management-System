package ru.example.projectservice.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import ru.example.projectservice.entities.enums.TaskStatus;
import ru.example.projectservice.entities.enums.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
public class Task extends AbstractEntity {

    @Column(name = "task_name")
    private String name;

    @Column(name = "task_description")
    private String description;

    @CreatedDate
    @Column(name = "task_created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "task_updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_type")
    private Type type;

    @Column(name = "task_start_date")
    private LocalDate startDate;

    @Column(name = "task_end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "assigner_id")
    private User assigner;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "release_id")
    private Release release;

    public Task() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public User getAssigner() {
        return assigner;
    }

    public void setAssigner(User assigner) {
        this.assigner = assigner;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }
}
