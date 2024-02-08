package ru.sharphurt.videohosting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.sharphurt.videohosting.dto.VideoFileInformationDto;
import ru.sharphurt.videohosting.entity.VideoEntity;

import java.nio.file.Path;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UploadVideoInformationMapper {

    UploadVideoInformationMapper VIDEO_INFORMATION_TO_ENTITY_MAPPER = Mappers.getMapper(UploadVideoInformationMapper.class);

    @Mapping(target = "processing", source = "processing", defaultValue = "false")
    @Mapping(target = "path", source = "path", qualifiedByName = "pathToString")
    VideoEntity dtoToEntity(VideoFileInformationDto dto);

    @Mapping(target = "path", source = "path", qualifiedByName = "stringToPath")
    VideoFileInformationDto entityToDto(VideoEntity entity);

    @Named("pathToString")
    static String pathToString(Path path) {
        return path.toString();
    }

    @Named("stringToPath")
    static Path stringToPath(String path) {
        return Path.of(path);
    }
}
