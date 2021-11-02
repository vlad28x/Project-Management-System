package ru.example.projectmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Сущность пользователя для ответа")
public class UserResponseDTO {
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
    @Schema(description = "Список сотрудников")
    private List<EmployeeRequestDTO> employees;

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

    public List<EmployeeRequestDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeRequestDTO> employees) {
        this.employees = employees;
    }
}
