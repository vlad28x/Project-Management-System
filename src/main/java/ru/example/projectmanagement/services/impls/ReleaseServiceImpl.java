package ru.example.projectmanagement.services.impls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.projectmanagement.entities.Release;
import ru.example.projectmanagement.entities.enums.Status;
import ru.example.projectmanagement.exceptions.BadRequestException;
import ru.example.projectmanagement.exceptions.NotFoundException;
import ru.example.projectmanagement.repositories.ReleaseRepository;
import ru.example.projectmanagement.services.ReleaseService;

import java.util.List;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private static final Logger log = LoggerFactory.getLogger(ReleaseServiceImpl.class);

    private final ReleaseRepository releaseRepository;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Release> getAll() {
        return releaseRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Release getById(Long id) {
        return releaseRepository.findById(id).orElseThrow(() -> {
            log.error(String.format("Release with ID %s not found", id));
            return new NotFoundException(String.format("Release with ID %s not found", id));
        });
    }

    @Override
    public Release add(Release release) {
        try {
            return releaseRepository.save(release);
        } catch (NestedRuntimeException e) {
            log.error(e.getMessage(), e.getCause());
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public Release update(Release release) {
        try {
            return releaseRepository.save(release);
        } catch (NestedRuntimeException e) {
            log.error(e.getMessage(), e.getCause());
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        releaseRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Long countUnderdoneTasks(Long id) {
        return releaseRepository.countByIdAndTasksStatusNot(id, Status.DONE);
    }
}
