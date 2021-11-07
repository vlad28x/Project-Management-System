package ru.example.projectmanagement.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<MemberResponseDto>> getAllEmployees() {
        List<MemberResponseDto> list = new ArrayList<>();
        list.add(new MemberResponseDto());
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Получение одного участника проекта",
            description = "Позволяет получить одного участника проекта по заданному ID")
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(new MemberResponseDto());
    }

    @Operation(summary = "Добавление участника в проект", description = "Позволяет добавить участника в проект")
    @PostMapping
    public ResponseEntity<MemberResponseDto> createEmployee(@RequestBody MemberRequestDto newEmployee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new MemberResponseDto());
    }

    @Operation(summary = "Обновление данных участника проекта",
            description = "Позволяет обновить данные участника проекта по заданному ID")
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateEmployee(@PathVariable Long id, @RequestBody MemberRequestDto newEmployee) {
        return ResponseEntity.ok(new MemberResponseDto());
    }

    @Operation(summary = "Удаление участника из проекта",
            description = "Позволяет удалить участника из проекта по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
    }
}
