package ru.example.projectmanagement.services;

import ru.example.projectmanagement.entities.Release;

import java.util.List;

public interface ReleaseService {

    List<Release> getAll();

    Release getById(Long id);

    Release add(Release release);

    Release update(Release release);

    void delete(Long id);
}
