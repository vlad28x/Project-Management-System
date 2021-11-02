package ru.example.projectmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сущность сотрудника для запроса")
public class EmployeeRequestDTO {
    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Пользователь")
    private UserRequestDTO user;
    @Schema(description = "Проект")
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
