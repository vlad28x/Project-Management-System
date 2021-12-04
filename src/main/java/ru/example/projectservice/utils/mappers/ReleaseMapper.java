package ru.example.projectservice.utils.mappers;

import org.mapstruct.Mapper;
import ru.example.projectservice.dto.ReleaseRequestDto;
import ru.example.projectservice.dto.ReleaseResponseDto;
import ru.example.projectservice.entities.Release;

@Mapper
public abstract class ReleaseMapper {

    public abstract ReleaseResponseDto releaseToReleaseResponseDto(Release release);

    public abstract Release releaseRequestDtoToRelease(ReleaseRequestDto release);
}
