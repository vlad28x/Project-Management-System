package ru.example.projectmanagement.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.example.projectmanagement.dto.ReleaseRequestDto;
import ru.example.projectmanagement.dto.ReleaseResponseDto;
import ru.example.projectmanagement.entities.Release;

@Mapper
public interface ReleaseMapper {

    ReleaseMapper INSTANCE = Mappers.getMapper(ReleaseMapper.class);

    ReleaseResponseDto releaseToReleaseResponseDto(Release release);

    Release releaseRequestDtoToRelease(ReleaseRequestDto release);
}
