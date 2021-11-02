package ru.example.projectmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Сущность релиза для ответа")
public class ReleaseResponseDTO {
    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Версия")
    private String version;
    @Schema(description = "Описание")
    private String description;
    @Schema(description = "Дата начала")
    private LocalDate startDate;
    @Schema(description = "Дата завершения")
    private LocalDate endDate;
    @Schema(description = "Список задач")
    private List<TaskResponseDTO> tasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public List<TaskResponseDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskResponseDTO> tasks) {
        this.tasks = tasks;
    }
}
