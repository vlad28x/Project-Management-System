package ru.example.projectmanagement.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.ReleaseRequestDto;
import ru.example.projectmanagement.dto.ReleaseResponseDto;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Релизы", description = "Взаимодействие с релизами")
@RestController
@RequestMapping("/releases")
public class ReleaseController {

    @Operation(summary = "Получение всех релизов", description = "Позволяет получить все релизы")
    @GetMapping
    public List<ReleaseResponseDto> getAllReleases() {
        List<ReleaseResponseDto> list = new ArrayList<>();
        list.add(new ReleaseResponseDto());
        return list;
    }

    @Operation(summary = "Получение одного релиза", description = "Позволяет получить один релиз по заданному ID")
    @GetMapping("/{id}")
    public ReleaseResponseDto getReleaseById(@PathVariable Long id) {
        return new ReleaseResponseDto();
    }

    @Operation(summary = "Добавление релиза", description = "Позволяет добавить релиз")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReleaseResponseDto createRelease(@RequestBody ReleaseRequestDto newRelease) {
        return new ReleaseResponseDto();
    }

    @Operation(summary = "Обновление релиза", description = "Позволяет обновить релиз по заданному ID")
    @PutMapping("/{id}")
    public ReleaseResponseDto updateRelease(@PathVariable Long id, @RequestBody ReleaseRequestDto newRelease) {
        return new ReleaseResponseDto();
    }

    @Operation(summary = "Удаление релиза", description = "Позволяет удалить релиз по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteRelease(@PathVariable Long id) {
    }
}
