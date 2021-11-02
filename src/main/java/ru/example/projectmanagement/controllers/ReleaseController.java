package ru.example.projectmanagement.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.projectmanagement.dto.ReleaseRequestDTO;
import ru.example.projectmanagement.dto.ReleaseResponseDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/releases")
public class ReleaseController {
    @GetMapping
    public List<ReleaseResponseDTO> getAllReleases() {
        List<ReleaseResponseDTO> list = new ArrayList<>();
        list.add(new ReleaseResponseDTO());
        return list;
    }

    @GetMapping("/{id}")
    public ReleaseResponseDTO getReleaseById(@PathVariable Long id) {
        return new ReleaseResponseDTO();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReleaseResponseDTO createRelease(@RequestBody ReleaseRequestDTO newRelease) {
        return new ReleaseResponseDTO();
    }

    @PutMapping("/{id}")
    public ReleaseResponseDTO updateRelease(@PathVariable Long id, @RequestBody ReleaseRequestDTO newRelease) {
        return new ReleaseResponseDTO();
    }

    @DeleteMapping("/{id}")
    public void deleteRelease(@PathVariable Long id) {
    }
}
