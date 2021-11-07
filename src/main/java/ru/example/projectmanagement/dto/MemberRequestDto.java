package ru.example.projectmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сущность участника проекта для запроса")
public class MemberRequestDto {
    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Пользователь")
    private UserRequestDto user;
    @Schema(description = "Проект")
    private ProjectRequestDto project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRequestDto getUser() {
        return user;
    }

    public void setUser(UserRequestDto user) {
        this.user = user;
    }

    public ProjectRequestDto getProject() {
        return project;
    }

    public void setProject(ProjectRequestDto project) {
        this.project = project;
    }
}
