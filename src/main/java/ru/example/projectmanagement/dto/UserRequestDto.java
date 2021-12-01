package ru.example.projectmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.example.projectmanagement.entities.enums.Role;

@Schema(description = "Сущность пользователя для запроса")
public class UserRequestDto {
    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Никнейм")
    private String username;
    @Schema(description = "Пароль")
    private String password;
    @Schema(description = "Имя")
    private String firstName;
    @Schema(description = "Фамилия")
    private String secondName;
    @Schema(description = "E-mail")
    private String email;
    @Schema(description = "Роль")
    private Role role;
    @Schema(description = "Проект")
    private Long projectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
