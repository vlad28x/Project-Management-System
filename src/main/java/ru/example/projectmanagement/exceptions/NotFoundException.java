package ru.example.projectmanagement.exceptions;

public class NotFoundException extends RuntimeException {

    private Long id;

    public NotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public NotFoundException(String message, Long id, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
