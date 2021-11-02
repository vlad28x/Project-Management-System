package ru.example.projectmanagement.dto;

public class EmployeeRequestDTO {
    private Long id;
    private UserRequestDTO user;
    private ProjectRequestDTO project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRequestDTO getUser() {
        return user;
    }

    public void setUser(UserRequestDTO user) {
        this.user = user;
    }

    public ProjectRequestDTO getProject() {
        return project;
    }

    public void setProject(ProjectRequestDTO project) {
        this.project = project;
    }
}
