package ru.example.projectmanagement.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "release")
public class Release extends AbstractEntity {

    @Column(name = "release_version")
    private String version;

    @Column(name = "release_description")
    private String description;

    @Column(name = "release_start_date")
    private LocalDate startDate;

    @Column(name = "release_end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "release")
    private List<Task> tasks;

    public Release() {
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
