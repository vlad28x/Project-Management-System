package ru.example.projectmanagement.exceptions;

public class ReleaseNotFoundException extends NotFoundException {
    public ReleaseNotFoundException() {
        super("Release not found");
    }
}
