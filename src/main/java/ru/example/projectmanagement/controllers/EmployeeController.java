package ru.example.projectmanagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.EmployeeRequestDTO;
import ru.example.projectmanagement.dto.EmployeeResponseDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<EmployeeResponseDTO> list = new ArrayList<>();
        list.add(new EmployeeResponseDTO());
        return list;
    }

    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable Long id) {
        return new EmployeeResponseDTO();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponseDTO createEmployee(@RequestBody EmployeeRequestDTO newEmployee) {
        return new EmployeeResponseDTO();
    }

    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDTO newEmployee) {
        return new EmployeeResponseDTO();
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
    }
}
