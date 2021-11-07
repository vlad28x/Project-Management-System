package ru.example.projectmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.example.projectmanagement.entities.enums.Status;
import ru.example.projectmanagement.entities.enums.Type;

import java.time.LocalDate;

@Schema(description = "Сущность задачи для запроса")
public class TaskRequestDto {
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
    private ReleaseRequestDto release;
    @Schema(description = "Создатель задачи")
    private MemberRequestDto owner;
    @Schema(description = "Назначенный на выполнение задачи")
    private MemberRequestDto assignee;
    @Schema(description = "Проект")
    private ProjectRequestDto project;

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

    public ReleaseRequestDto getRelease() {
        return release;
    }

    public void setRelease(ReleaseRequestDto release) {
        this.release = release;
    }

    public MemberRequestDto getOwner() {
        return owner;
    }

    public void setOwner(MemberRequestDto owner) {
        this.owner = owner;
    }

    public MemberRequestDto getAssignee() {
        return assignee;
    }

    public void setAssignee(MemberRequestDto assignee) {
        this.assignee = assignee;
    }

    public ProjectRequestDto getProject() {
        return project;
    }

    public void setProject(ProjectRequestDto project) {
        this.project = project;
    }
}
