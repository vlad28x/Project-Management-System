package ru.example.projectmanagement.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.MemberRequestDto;
import ru.example.projectmanagement.dto.MemberResponseDto;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Участники проекта", description = "Взаимодействие с участниками проекта")
@RestController
@RequestMapping("/members")
public class MemberController {

    @Operation(summary = "Получение всех участников всех проектов",
            description = "Позволяет получить всех участников всех проектов")
    @GetMapping
    public List<MemberResponseDto> getAllEmployees() {
        List<MemberResponseDto> list = new ArrayList<>();
        list.add(new MemberResponseDto());
        return list;
    }

    @Operation(summary = "Получение одного участника проекта",
            description = "Позволяет получить одного участника проекта по заданному ID")
    @GetMapping("/{id}")
    public MemberResponseDto getEmployeeById(@PathVariable Long id) {
        return new MemberResponseDto();
    }

    @Operation(summary = "Добавление участника в проект", description = "Позволяет добавить участника в проект")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponseDto createEmployee(@RequestBody MemberRequestDto newEmployee) {
        return new MemberResponseDto();
    }

    @Operation(summary = "Обновление данных участника проекта",
            description = "Позволяет обновить данные участника проекта по заданному ID")
    @PutMapping("/{id}")
    public MemberResponseDto updateEmployee(@PathVariable Long id, @RequestBody MemberRequestDto newEmployee) {
        return new MemberResponseDto();
    }

    @Operation(summary = "Удаление участника из проекта",
            description = "Позволяет удалить участника из проекта по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
    }
}
