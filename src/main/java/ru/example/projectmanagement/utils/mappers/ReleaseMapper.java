package ru.example.projectmanagement.utils.mappers;

import org.mapstruct.Mapper;
import ru.example.projectmanagement.dto.ReleaseRequestDto;
import ru.example.projectmanagement.dto.ReleaseResponseDto;
import ru.example.projectmanagement.entities.Release;

@Mapper
public abstract class ReleaseMapper {

    public abstract ReleaseResponseDto releaseToReleaseResponseDto(Release release);

    public abstract Release releaseRequestDtoToRelease(ReleaseRequestDto release);
}
