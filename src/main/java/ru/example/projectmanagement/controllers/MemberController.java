package ru.example.projectmanagement.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.MemberRequestDTO;
import ru.example.projectmanagement.dto.MemberResponseDTO;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Участники проекта", description = "Взаимодействие с участниками проекта")
@RestController
@RequestMapping("/members")
public class MemberController {

    @Operation(summary = "Получение всех участников всех проектов",
            description = "Позволяет получить всех участников всех проектов")
    @GetMapping
    public List<MemberResponseDTO> getAllEmployees() {
        List<MemberResponseDTO> list = new ArrayList<>();
        list.add(new MemberResponseDTO());
        return list;
    }

    @Operation(summary = "Получение одного участника проекта",
            description = "Позволяет получить одного участника проекта по заданному ID")
    @GetMapping("/{id}")
    public MemberResponseDTO getEmployeeById(@PathVariable Long id) {
        return new MemberResponseDTO();
    }

    @Operation(summary = "Добавление участника в проект", description = "Позволяет добавить участника в проект")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponseDTO createEmployee(@RequestBody MemberRequestDTO newEmployee) {
        return new MemberResponseDTO();
    }

    @Operation(summary = "Обновление данных участника проекта",
            description = "Позволяет обновить данные участника проекта по заданному ID")
    @PutMapping("/{id}")
    public MemberResponseDTO updateEmployee(@PathVariable Long id, @RequestBody MemberRequestDTO newEmployee) {
        return new MemberResponseDTO();
    }

    @Operation(summary = "Удаление участника из проекта",
            description = "Позволяет удалить участника из проекта по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
    }
}
