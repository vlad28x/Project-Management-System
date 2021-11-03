package ru.example.projectmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Сущность участника проекта для ответа")
public class MemberResponseDTO {
    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Пользователь")
    private TaskRequestDTO user;
    @Schema(description = "Проект")
    private TaskRequestDTO project;
    @Schema(description = "Список выполняемых участником задач")
    private List<TaskRequestDTO> assignedTasks;
    @Schema(description = "Список созданных участником задач")
    private List<TaskRequestDTO> ownerTasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskRequestDTO getUser() {
        return user;
    }

    public void setUser(TaskRequestDTO user) {
        this.user = user;
    }

    public TaskRequestDTO getProject() {
        return project;
    }

    public void setProject(TaskRequestDTO project) {
        this.project = project;
    }

    public List<TaskRequestDTO> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<TaskRequestDTO> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    public List<TaskRequestDTO> getOwnerTasks() {
        return ownerTasks;
    }

    public void setOwnerTasks(List<TaskRequestDTO> ownerTasks) {
        this.ownerTasks = ownerTasks;
    }
}
