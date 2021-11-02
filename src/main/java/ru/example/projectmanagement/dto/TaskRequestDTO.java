package ru.example.projectmanagement.dto;

import ru.example.projectmanagement.entities.enums.Status;
import ru.example.projectmanagement.entities.enums.Type;

import java.time.LocalDate;

public class TaskRequestDTO {
    private Long id;
    private String name;
    private String description;
    private Status status;
    private Type type;
    private LocalDate startDate;
    private LocalDate endDate;
    private ReleaseRequestDTO release;
    private EmployeeRequestDTO owner;
    private EmployeeRequestDTO assigner;
    private ProjectRequestDTO project;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public ReleaseRequestDTO getRelease() {
        return release;
    }

    public void setRelease(ReleaseRequestDTO release) {
        this.release = release;
    }

    public EmployeeRequestDTO getOwner() {
        return owner;
    }

    public void setOwner(EmployeeRequestDTO owner) {
        this.owner = owner;
    }

    public EmployeeRequestDTO getAssigner() {
        return assigner;
    }

    public void setAssigner(EmployeeRequestDTO assigner) {
        this.assigner = assigner;
    }

    public ProjectRequestDTO getProject() {
        return project;
    }

    public void setProject(ProjectRequestDTO project) {
        this.project = project;
    }
}
