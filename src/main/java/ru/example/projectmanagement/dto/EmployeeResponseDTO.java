package ru.example.projectmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Сущность сотрудника для ответа")
public class EmployeeResponseDTO {
    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Пользователь")
    private UserResponseDTO user;
    @Schema(description = "Проект")
    private ProjectResponseDTO project;
    @Schema(description = "Список выполняемых сотрудником задач")
    private List<TaskResponseDTO> assignedTasks;
    @Schema(description = "Список созданных сотрудником задач")
    private List<TaskResponseDTO> ownerTasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public ProjectResponseDTO getProject() {
        return project;
    }

    public void setProject(ProjectResponseDTO project) {
        this.project = project;
    }

    public List<TaskResponseDTO> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<TaskResponseDTO> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    public List<TaskResponseDTO> getOwnerTasks() {
        return ownerTasks;
    }

    public void setOwnerTasks(List<TaskResponseDTO> ownerTasks) {
        this.ownerTasks = ownerTasks;
    }
}
