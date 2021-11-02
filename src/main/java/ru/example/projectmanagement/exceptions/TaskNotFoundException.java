package ru.example.projectmanagement.exceptions;

public class TaskNotFoundException extends NotFoundException {
    public TaskNotFoundException() {
        super("Task not found");
    }
}
