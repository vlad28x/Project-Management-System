package ru.example.projectmanagement.services;

import ru.example.projectmanagement.dto.ReleaseRequestDto;
import ru.example.projectmanagement.dto.ReleaseResponseDto;
import ru.example.projectmanagement.entities.Release;

import java.util.List;

/**
 * Service for release.
 */
public interface ReleaseService {

    /**
     * Get all releases.
     *
     * @return list of ReleaseResponseDto
     */
    List<ReleaseResponseDto> getAll();

    /**
     * Get specific release.
     *
     * @param id - identifier of release
     * @return ReleaseResponseDto
     */
    ReleaseResponseDto getById(Long id);

    /**
     * Add release.
     *
     * @param newRelease - ReleaseRequestDto
     * @return ReleaseResponseDto
     */
    ReleaseResponseDto add(ReleaseRequestDto newRelease);

    /**
     * Update release.
     *
     * @param newRelease - ReleaseRequestDto
     * @return ReleaseResponseDto
     */
    ReleaseResponseDto update(ReleaseRequestDto newRelease);

    /**
     * Delete specific release.
     *
     * @param id - identifier of release
     */
    void delete(Long id);

    /**
     * Count underdone tasks in specific release.
     *
     * @param id - identifier of release
     * @return the number of tasks
     */
    Long countUnderdoneTasks(Long id);
}
