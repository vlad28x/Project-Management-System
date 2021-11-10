package ru.example.projectmanagement.services.impls;

import org.springframework.stereotype.Service;
import ru.example.projectmanagement.entities.Project;
import ru.example.projectmanagement.exceptions.BadRequestException;
import ru.example.projectmanagement.exceptions.NotFoundException;
import ru.example.projectmanagement.repositories.ProjectRepository;
import ru.example.projectmanagement.services.ProjectService;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project getById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Проект не найден", id));
    }

    @Override
    public Project add(Project project) {
        try {
            return projectRepository.save(project);
        } catch (RuntimeException e) {
            throw new BadRequestException("Неверный запрос");
        }
    }

    @Override
    public Project update(Project project) {
        try {
            return projectRepository.save(project);
        } catch (RuntimeException e) {
            throw new BadRequestException("Неверный запрос");
        }
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
