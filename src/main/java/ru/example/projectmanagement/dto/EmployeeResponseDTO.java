package ru.example.projectmanagement.dto;

import java.util.List;

public class EmployeeResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private ProjectResponseDTO project;
    private List<TaskResponseDTO> assignedTasks;
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
