package ru.example.projectmanagement.services.impls;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.projectmanagement.dto.ReleaseRequestDto;
import ru.example.projectmanagement.dto.ReleaseResponseDto;
import ru.example.projectmanagement.entities.Release;
import ru.example.projectmanagement.entities.enums.Status;
import ru.example.projectmanagement.exceptions.BadRequestException;
import ru.example.projectmanagement.exceptions.NotFoundException;
import ru.example.projectmanagement.repositories.ReleaseRepository;
import ru.example.projectmanagement.services.ReleaseService;
import ru.example.projectmanagement.utils.mappers.ReleaseMapper;

import java.util.List;
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
            return new NotFoundException(String.format("Release with ID %s not found", id));
        }));
    }

    @Override
    public ReleaseResponseDto add(ReleaseRequestDto newRelease) {
        try {
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
        try {
            return releaseMapper.releaseToReleaseResponseDto(releaseRepository.save(
                    releaseMapper.releaseRequestDtoToRelease(newRelease)
            ));
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
