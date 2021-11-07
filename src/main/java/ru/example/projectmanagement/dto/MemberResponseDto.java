package ru.example.projectmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Сущность участника проекта для ответа")
public class MemberResponseDto {
    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Пользователь")
    private TaskRequestDto user;
    @Schema(description = "Проект")
    private TaskRequestDto project;
    @Schema(description = "Список выполняемых участником задач")
    private List<TaskRequestDto> assignedTasks;
    @Schema(description = "Список созданных участником задач")
    private List<TaskRequestDto> ownerTasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskRequestDto getUser() {
        return user;
    }

    public void setUser(TaskRequestDto user) {
        this.user = user;
    }

    public TaskRequestDto getProject() {
        return project;
    }

    public void setProject(TaskRequestDto project) {
        this.project = project;
    }

    public List<TaskRequestDto> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<TaskRequestDto> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    public List<TaskRequestDto> getOwnerTasks() {
        return ownerTasks;
    }

    public void setOwnerTasks(List<TaskRequestDto> ownerTasks) {
        this.ownerTasks = ownerTasks;
    }
}
