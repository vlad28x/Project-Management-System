package ru.example.projectmanagement.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.ReleaseRequestDto;
import ru.example.projectmanagement.dto.ReleaseResponseDto;
import ru.example.projectmanagement.entities.Release;
import ru.example.projectmanagement.services.ReleaseService;
import ru.example.projectmanagement.utils.mappers.ReleaseMapper;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Релизы", description = "Взаимодействие с релизами")
@RestController
@RequestMapping("/releases")
public class ReleaseController {

    private final ReleaseService releaseService;

    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Получение всех релизов", description = "Позволяет получить все релизы")
    @GetMapping
    public ResponseEntity<List<ReleaseResponseDto>> getAllReleases() {
        return ResponseEntity.ok(releaseService.getAll());
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Получение одного релиза", description = "Позволяет получить один релиз по заданному ID")
    @GetMapping("/{id}")
    public ResponseEntity<ReleaseResponseDto> getReleaseById(@PathVariable Long id) {
        return ResponseEntity.ok(releaseService.getById(id));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Добавление релиза", description = "Позволяет добавить релиз")
    @PostMapping
    public ResponseEntity<ReleaseResponseDto> createRelease(@RequestBody ReleaseRequestDto newRelease) {
        return ResponseEntity.status(HttpStatus.CREATED).body(releaseService.add(newRelease));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Обновление релиза", description = "Позволяет обновить релиз по заданному ID")
    @PutMapping("/{id}")
    public ResponseEntity<ReleaseResponseDto> updateRelease(@PathVariable Long id, @RequestBody ReleaseRequestDto newRelease) {
        return ResponseEntity.ok(releaseService.update(newRelease));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Удаление релиза", description = "Позволяет удалить релиз по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteRelease(@PathVariable Long id) {
        releaseService.delete(id);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Подсчет количества невыполненных задач в заданный релиз",
            description = "Позволяет посчитать количество невыполненных задач по заданному ID релиза")
    @GetMapping("{id}/countUnderdoneTasks")
    public ResponseEntity<Long> countUnderdoneTasks(@PathVariable Long id) {
        return ResponseEntity.ok(releaseService.countUnderdoneTasks(id));
    }
}
