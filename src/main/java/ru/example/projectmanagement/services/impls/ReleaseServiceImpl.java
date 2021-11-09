package ru.example.projectmanagement.services.impls;

import org.springframework.stereotype.Service;
import ru.example.projectmanagement.entities.Release;
import ru.example.projectmanagement.exceptions.NotFoundException;
import ru.example.projectmanagement.repositories.ReleaseRepository;
import ru.example.projectmanagement.services.ReleaseService;

import java.util.List;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private ReleaseRepository releaseRepository;

    @Override
    public List<Release> getAll() {
        return releaseRepository.findAll();
    }

    @Override
    public Release getById(Long id) {
        return releaseRepository.findById(id).orElseThrow(() -> new NotFoundException("Релиз не найден", id));
    }

    @Override
    public Release add(Release release) {
        return releaseRepository.save(release);
    }

    @Override
    public Release update(Release release) {
        return releaseRepository.save(release);
    }

    @Override
    public void delete(Long id) {
        releaseRepository.deleteById(id);
    }
}