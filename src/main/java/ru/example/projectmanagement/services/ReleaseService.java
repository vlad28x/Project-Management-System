package ru.example.projectmanagement.services;

import ru.example.projectmanagement.entities.Release;

import java.util.List;

/**
 * Service for release.
 */
public interface ReleaseService {

    /**
     * Get all releases.
     *
     * @return list of releases
     */
    List<Release> getAll();

    /**
     * Get specific release.
     *
     * @param id - identifier of release
     * @return release entity
     */
    Release getById(Long id);

    /**
     * Add release.
     *
     * @param release - release entity
     * @return release entity
     */
    Release add(Release release);

    /**
     * Update release.
     *
     * @param release - release entity
     * @return release entity
     */
    Release update(Release release);

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
