package ru.example.projectservice.services.impls;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.projectservice.dto.ReleaseRequestDto;
import ru.example.projectservice.dto.ReleaseResponseDto;
import ru.example.projectservice.entities.enums.TaskStatus;
import ru.example.projectservice.exceptions.BadRequestException;
import ru.example.projectservice.exceptions.NotFoundException;
import ru.example.projectservice.repositories.ReleaseRepository;
import ru.example.projectservice.services.ReleaseService;
import ru.example.projectservice.utils.mappers.ReleaseMapper;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private static final Logger log = LoggerFactory.getLogger(ReleaseServiceImpl.class);
    private static final ReleaseMapper releaseMapper = Mappers.getMapper(ReleaseMapper.class);

    private final ReleaseRepository releaseRepository;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReleaseResponseDto> getAll() {
        return releaseRepository.findAll().stream()
                .map(releaseMapper::releaseToReleaseResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ReleaseResponseDto getById(Long id) {
        return releaseMapper.releaseToReleaseResponseDto(releaseRepository.findById(id).orElseThrow(() -> {
            log.error(String.format("Release with ID %s not found", id));
            return new NotFoundException(String.format(ResourceBundle.getBundle("messages").getString("exception.releaseNotFound"), id));
        }));
    }

    @Override
    public ReleaseResponseDto add(ReleaseRequestDto newRelease) {
        try {
            newRelease.setId(null);
            return releaseMapper.releaseToReleaseResponseDto(releaseRepository.save(
                    releaseMapper.releaseRequestDtoToRelease(newRelease)
            ));
        } catch (NestedRuntimeException e) {
            log.error(e.getMessage(), e.getCause());
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public ReleaseResponseDto update(ReleaseRequestDto newRelease) {
        if (newRelease == null || newRelease.getId() == null) {
            log.error("Release or ID must not be null!");
            throw new BadRequestException(ResourceBundle.getBundle("messages").getString("exception.releaseOrReleaseIdNull"));
        }
        if (releaseRepository.findById(newRelease.getId()).isPresent()) {
            try {
                return releaseMapper.releaseToReleaseResponseDto(releaseRepository.save(
                        releaseMapper.releaseRequestDtoToRelease(newRelease)
                ));
            } catch (NestedRuntimeException e) {
                log.error(e.getMessage(), e.getCause());
                throw new BadRequestException(e.getMessage());
            }
        } else {
            log.error(String.format("Release with ID %s not found", newRelease.getId()));
            throw new NotFoundException(String.format(ResourceBundle.getBundle("messages").getString("exception.releaseNotFound"), newRelease.getId()));
        }

    }

    @Override
    public void delete(Long id) {
        getById(id);
        releaseRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Long countUnderdoneTasks(Long id) {
        return releaseRepository.countByIdAndTasksStatusNot(id, TaskStatus.DONE);
    }
}
