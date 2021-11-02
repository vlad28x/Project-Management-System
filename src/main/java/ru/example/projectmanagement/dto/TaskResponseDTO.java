package ru.example.projectmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.example.projectmanagement.entities.enums.Status;
import ru.example.projectmanagement.entities.enums.Type;

import java.time.LocalDate;

@Schema(description = "Сущность задачи для ответа")
public class TaskResponseDTO {
    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Имя")
    private String name;
    @Schema(description = "Описание")
    private String description;
    @Schema(description = "Статус")
    private Status status;
    @Schema(description = "Тип")
    private Type type;
    @Schema(description = "Дата начала задачи")
    private LocalDate startDate;
    @Schema(description = "Дата завершения задачи")
    private LocalDate endDate;
    @Schema(description = "Релиз")
    private ReleaseResponseDTO release;
    @Schema(description = "Создатель")
    private EmployeeResponseDTO owner;
    @Schema(description = "Назначенный")
    private EmployeeResponseDTO assignee;
    @Schema(description = "Назначенный")
    private ProjectResponseDTO project;

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

    public ReleaseResponseDTO getRelease() {
        return release;
    }

    public void setRelease(ReleaseResponseDTO release) {
        this.release = release;
    }

    public EmployeeResponseDTO getOwner() {
        return owner;
    }

    public void setOwner(EmployeeResponseDTO owner) {
        this.owner = owner;
    }

    public EmployeeResponseDTO getAssignee() {
        return assignee;
    }

    public void setAssignee(EmployeeResponseDTO assignee) {
        this.assignee = assignee;
    }

    public ProjectResponseDTO getProject() {
        return project;
    }

    public void setProject(ProjectResponseDTO project) {
        this.project = project;
    }
}
