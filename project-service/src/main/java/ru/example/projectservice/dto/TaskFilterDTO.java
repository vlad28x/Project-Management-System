package ru.example.projectservice.dto;

import ru.example.projectservice.services.specifications.Operation;

public class TaskFilterDTO {

    private String key;
    private Operation operation;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isHasNullValue() {
        return key == null || operation == null || value == null;
    }
}
