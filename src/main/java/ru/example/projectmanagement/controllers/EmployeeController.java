package ru.example.projectmanagement.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.EmployeeRequestDTO;
import ru.example.projectmanagement.dto.EmployeeResponseDTO;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Сотрудники", description = "Взаимодействие с сотрудниками")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Operation(summary = "Получение всех сотрудников", description = "Позволяет получить всех сотрудников")
    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<EmployeeResponseDTO> list = new ArrayList<>();
        list.add(new EmployeeResponseDTO());
        return list;
    }

    @Operation(summary = "Получение одного сотрудника",
            description = "Позволяет получить одного сотрудника по заданному ID")
    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable Long id) {
        return new EmployeeResponseDTO();
    }

    @Operation(summary = "Добавление сотрудника", description = "Позволяет добавить сотрудника")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponseDTO createEmployee(@RequestBody EmployeeRequestDTO newEmployee) {
        return new EmployeeResponseDTO();
    }

    @Operation(summary = "Обновление сотрудника", description = "Позволяет обновить сотрудника по заданному ID")
    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDTO newEmployee) {
        return new EmployeeResponseDTO();
    }

    @Operation(summary = "Удаление сотрудника", description = "Позволяет удалить сотрудника по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
    }
}
