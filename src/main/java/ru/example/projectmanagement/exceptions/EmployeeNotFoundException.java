package ru.example.projectmanagement.exceptions;

public class EmployeeNotFoundException extends NotFoundException {
    public EmployeeNotFoundException() {
        super("Employee not found");
    }
}
