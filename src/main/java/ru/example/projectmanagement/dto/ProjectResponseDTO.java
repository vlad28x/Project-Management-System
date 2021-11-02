package ru.example.projectmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.example.projectmanagement.entities.enums.Status;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Сущность проекта для ответа")
public class ProjectResponseDTO {
    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Имя")
    private String name;
    @Schema(description = "Описание")
    private String description;
    @Schema(description = "Дата начала проекта")
    private LocalDate startDate;
    @Schema(description = "Дата завершения проекта")
    private LocalDate endDate;
    @Schema(description = "Статус")
    private Status status;
    @Schema(description = "Создатель")
    private EmployeeResponseDTO owner;
    @Schema(description = "Список задач")
    private List<TaskResponseDTO> tasks;
    @Schema(description = "Список сотрудников")
    private List<TaskResponseDTO> employees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public EmployeeResponseDTO getOwner() {
        return owner;
    }

    public void setOwner(EmployeeResponseDTO owner) {
        this.owner = owner;
    }

    public List<TaskResponseDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskResponseDTO> tasks) {
        this.tasks = tasks;
    }

    public List<TaskResponseDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<TaskResponseDTO> employees) {
        this.employees = employees;
    }
}
