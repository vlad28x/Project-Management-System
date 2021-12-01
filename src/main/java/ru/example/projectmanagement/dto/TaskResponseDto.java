package ru.example.projectmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.example.projectmanagement.entities.enums.Status;
import ru.example.projectmanagement.entities.enums.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "Сущность задачи для ответа")
public class TaskResponseDto {
    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Имя")
    private String name;
    @Schema(description = "Описание")
    private String description;
    @Schema(description = "Время создания")
    private LocalDateTime createdAt;
    @Schema(description = "Время обновления")
    private LocalDateTime updatedAt;
    @Schema(description = "Статус")
    private Status status;
    @Schema(description = "Тип")
    private Type type;
    @Schema(description = "Дата начала задачи")
    private LocalDate startDate;
    @Schema(description = "Дата завершения задачи")
    private LocalDate endDate;
    @Schema(description = "Назначенный на выполнение задачи")
    private UserResponseDto assigner;
    @Schema(description = "Создатель задачи")
    private UserResponseDto owner;
    @Schema(description = "Проект")
    private ProjectResponseDto project;
    @Schema(description = "Релиз")
    private ReleaseResponseDto release;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

    public ReleaseResponseDto getRelease() {
        return release;
    }

    public void setRelease(ReleaseResponseDto release) {
        this.release = release;
    }

    public UserResponseDto getOwner() {
        return owner;
    }

    public void setOwner(UserResponseDto owner) {
        this.owner = owner;
    }

    public UserResponseDto getAssigner() {
        return assigner;
    }

    public void setAssigner(UserResponseDto assigner) {
        this.assigner = assigner;
    }

    public ProjectResponseDto getProject() {
        return project;
    }

    public void setProject(ProjectResponseDto project) {
        this.project = project;
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
}
