package ru.example.projectmanagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.example.projectmanagement.exceptions.NotFoundException;

import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private class Response {
        private LocalDateTime time;
        private String message;
        private Long id;

        public LocalDateTime getTime() {
            return time;
        }

        public void setTime(LocalDateTime time) {
            this.time = time;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleNotFound(NotFoundException e) {
        Response response = new Response();
        response.time = LocalDateTime.now();
        response.message = e.getMessage();
        response.id = e.getId();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
