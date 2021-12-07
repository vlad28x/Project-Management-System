package ru.example.projectservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.example.projectservice.entities.enums.ProjectStatus;

import java.time.LocalDate;

@Schema(description = "Сущность проекта для ответа")
public class ProjectResponseDto {
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
    @Schema(description = "Задолженность")
    private Integer debt;
    @Schema(description = "Статус")
    private ProjectStatus status;
    @Schema(description = "Создатель проекта")
    private Long ownerId;
    @Schema(description = "Заказчик")
    private Long customerId;

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

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getDebt() {
        return debt;
    }

    public void setDebt(Integer debt) {
        this.debt = debt;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
