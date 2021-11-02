package ru.example.projectmanagement.exceptions;

public class ProjectNotFoundException extends NotFoundException {
    public ProjectNotFoundException() {
        super("Project not found");
    }
}
