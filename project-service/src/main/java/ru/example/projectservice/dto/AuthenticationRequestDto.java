package ru.example.projectservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сущность аутентификации для запроса")
public class AuthenticationRequestDto {
    @Schema(description = "Никнейм")
    private String username;
    @Schema(description = "Пароль")
    private String password;

    public AuthenticationRequestDto() {
    }

    public AuthenticationRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
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
}
