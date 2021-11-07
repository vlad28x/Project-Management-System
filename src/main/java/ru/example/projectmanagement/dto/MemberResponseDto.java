package ru.example.projectmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.example.projectmanagement.entities.enums.Role;

@Schema(description = "Сущность участника проекта для ответа")
public class MemberResponseDto {
    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Пользователь")
    private Long userId;
    @Schema(description = "Проект")
    private Long projectId;
    @Schema(description = "Роль")
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
