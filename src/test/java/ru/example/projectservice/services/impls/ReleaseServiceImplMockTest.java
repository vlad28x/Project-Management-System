package ru.example.projectservice.services.impls;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.example.projectservice.dto.ReleaseRequestDto;
import ru.example.projectservice.dto.ReleaseResponseDto;
import ru.example.projectservice.entities.Release;
import ru.example.projectservice.entities.enums.TaskStatus;
import ru.example.projectservice.exceptions.BadRequestException;
import ru.example.projectservice.exceptions.NotFoundException;
import ru.example.projectservice.repositories.ReleaseRepository;
import ru.example.projectservice.utils.mappers.ReleaseMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ReleaseServiceImplMockTest {

    private static final ReleaseMapper releaseMapper = Mappers.getMapper(ReleaseMapper.class);

    @InjectMocks
    private ReleaseServiceImpl releaseService;

    @Mock
    private ReleaseRepository releaseRepository;

    @Test
    void getAllReleasesTest() {
        List<Release> list = new ArrayList<>(Arrays.asList(new Release(), new Release()));
        Mockito.when(releaseRepository.findAll()).thenReturn(list);
        List<ReleaseResponseDto> excepted = releaseService.getAll();
        assertEquals(excepted.size(), list.size());
    }

    @Test
    void getReleaseByIdSuccessTest() {
        Release release = new Release();
        release.setId(1L);
        release.setVersion("1.0 Beta");
        release.setDescription("Бета версия проекта");
        release.setStartDate(LocalDate.of(2021, 10, 25));
        release.setEndDate(LocalDate.of(2021, 11, 2));

        Mockito.when(releaseRepository.findById(1L)).thenReturn(Optional.of(release));

        ReleaseResponseDto actual = releaseService.getById(1L);
        ReleaseResponseDto expected = releaseMapper.releaseToReleaseResponseDto(release);

        assertEqualsReleaseResponseDto(expected, actual);
    }

    @Test
    void getReleaseByFailTest() {
        Mockito.when(releaseRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> releaseService.getById(1L));
    }

    @Test
    void addReleaseSuccessTest() {
        ReleaseRequestDto releaseRequestDto = new ReleaseRequestDto();
        releaseRequestDto.setId(1L);
        releaseRequestDto.setVersion("1.0 Beta");
        releaseRequestDto.setDescription("Бета версия проекта");
        releaseRequestDto.setStartDate(LocalDate.of(2021, 10, 25));
        releaseRequestDto.setEndDate(LocalDate.of(2021, 11, 2));

        Release release = releaseMapper.releaseRequestDtoToRelease(releaseRequestDto);
        Mockito.when(releaseRepository.save(Mockito.any(Release.class))).thenReturn(release);

        ReleaseResponseDto actual = releaseService.add(releaseRequestDto);
        ReleaseResponseDto expected = releaseMapper.releaseToReleaseResponseDto(release);

        assertEqualsReleaseResponseDto(expected, actual);
    }

    @Test
    void addReleaseFailTest() {
        ReleaseRequestDto releaseRequestDto = new ReleaseRequestDto();
        Mockito.when(releaseRepository.save(Mockito.any(Release.class))).thenThrow(BadRequestException.class);
        assertThrows(BadRequestException.class, () -> releaseService.add(releaseRequestDto));
    }

    @Test
    void updateReleaseSuccessTest() {
        ReleaseRequestDto releaseRequestDto = new ReleaseRequestDto();
        releaseRequestDto.setId(1L);
        releaseRequestDto.setVersion("1.0 Beta");
        releaseRequestDto.setDescription("Бета версия проекта");
        releaseRequestDto.setStartDate(LocalDate.of(2021, 10, 25));
        releaseRequestDto.setEndDate(LocalDate.of(2021, 11, 2));

        Release release = releaseMapper.releaseRequestDtoToRelease(releaseRequestDto);
        Mockito.when(releaseRepository.save(Mockito.any(Release.class))).thenReturn(release);
        Mockito.when(releaseRepository.findById(1L)).thenReturn(Optional.of(release));

        ReleaseResponseDto actual = releaseService.update(releaseRequestDto);
        ReleaseResponseDto expected = releaseMapper.releaseToReleaseResponseDto(release);

        assertEqualsReleaseResponseDto(expected, actual);
    }

    @Test
    void updateReleaseFailTest() {
        ReleaseRequestDto releaseRequestDto = new ReleaseRequestDto();
        releaseRequestDto.setId(1L);
        Mockito.when(releaseRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> releaseService.update(releaseRequestDto));
    }

    @Test
    void deleteReleaseSuccessTest() {
        Mockito.when(releaseRepository.findById(1L)).thenReturn(Optional.of(new Release()));
        releaseService.delete(1L);
        Mockito.verify(releaseRepository, Mockito.times(1)).deleteById(Mockito.any(Long.class));
    }

    @Test
    void deleteReleaseFailTest() {
        Mockito.when(releaseRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> releaseService.delete(1L));
        Mockito.verify(releaseRepository, Mockito.times(0)).deleteById(Mockito.any(Long.class));
    }

    @Test
    void countUnderdoneTasksTest() {
        Long id = 1L;
        Mockito.when(releaseRepository.countByIdAndTasksStatusNot(Mockito.eq(id), Mockito.any(TaskStatus.class))).thenReturn(15L);
        assertEquals(15L, releaseService.countUnderdoneTasks(id));
    }

    private void assertEqualsReleaseResponseDto(ReleaseResponseDto expected, ReleaseResponseDto actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getVersion(), actual.getVersion());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getStartDate(), actual.getStartDate());
        assertEquals(expected.getEndDate(), actual.getEndDate());
    }
}