package ru.sharphurt.videohosting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.sharphurt.videohosting.dto.VideoFileInformationDto;
import ru.sharphurt.videohosting.entity.VideoEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UploadVideoInformationMapper {

    UploadVideoInformationMapper VIDEO_INFORMATION_TO_ENTITY_MAPPER = Mappers.getMapper(UploadVideoInformationMapper.class);

    VideoEntity dtoToEntity(VideoFileInformationDto dto);
}
