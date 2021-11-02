package ru.example.projectmanagement.dto;

import ru.example.projectmanagement.entities.enums.Status;

import java.time.LocalDate;
import java.util.List;

public class ProjectResponseDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
    private EmployeeResponseDTO owner;
    private List<TaskResponseDTO> tasks;
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
