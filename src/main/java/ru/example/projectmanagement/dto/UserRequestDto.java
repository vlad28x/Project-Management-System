package ru.example.projectmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сущность пользователя для запроса")
public class UserRequestDto {
    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Никнейм")
    private String username;
    @Schema(description = "Имя")
    private String firstName;
    @Schema(description = "Фамилия")
    private String secondName;
    @Schema(description = "E-mail")
    private String email;

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
}
