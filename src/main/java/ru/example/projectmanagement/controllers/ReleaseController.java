package ru.example.projectmanagement.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.ReleaseRequestDTO;
import ru.example.projectmanagement.dto.ReleaseResponseDTO;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Релизы", description = "Взаимодействие с релизами")
@RestController
@RequestMapping("/releases")
public class ReleaseController {

    @Operation(summary = "Получение всех релизов", description = "Позволяет получить все релизы")
    @GetMapping
    public List<ReleaseResponseDTO> getAllReleases() {
        List<ReleaseResponseDTO> list = new ArrayList<>();
        list.add(new ReleaseResponseDTO());
        return list;
    }

    @Operation(summary = "Получение одного релиза", description = "Позволяет получить один релиз по заданному ID")
    @GetMapping("/{id}")
    public ReleaseResponseDTO getReleaseById(@PathVariable Long id) {
        return new ReleaseResponseDTO();
    }

    @Operation(summary = "Добавление релиза", description = "Позволяет добавить релиз")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReleaseResponseDTO createRelease(@RequestBody ReleaseRequestDTO newRelease) {
        return new ReleaseResponseDTO();
    }

    @Operation(summary = "Обновление релиза", description = "Позволяет обновить релиз по заданному ID")
    @PutMapping("/{id}")
    public ReleaseResponseDTO updateRelease(@PathVariable Long id, @RequestBody ReleaseRequestDTO newRelease) {
        return new ReleaseResponseDTO();
    }

    @Operation(summary = "Удаление релиза", description = "Позволяет удалить релиз по заданному ID")
    @DeleteMapping("/{id}")
    public void deleteRelease(@PathVariable Long id) {
    }
}
