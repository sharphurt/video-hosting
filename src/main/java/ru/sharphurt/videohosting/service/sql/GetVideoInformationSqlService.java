package ru.sharphurt.videohosting.service.sql;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sharphurt.videohosting.dto.VideoFileInformationDto;
import ru.sharphurt.videohosting.exceptions.VideoNotFoundException;
import ru.sharphurt.videohosting.repository.VideoRepository;

import java.util.UUID;

import static ru.sharphurt.videohosting.constants.AliasConstants.LOG_INFORMATION_GOT;
import static ru.sharphurt.videohosting.mapper.UploadVideoInformationMapper.VIDEO_ENTITY_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetVideoInformationSqlService {

    private final VideoRepository repository;

    private final String serviceName = "get-video-information-database-service";

    public VideoFileInformationDto getVideoInformation(UUID uuid) {
        var entity = repository.findById(uuid).orElseThrow(() -> new VideoNotFoundException(serviceName, uuid.toString()));

        log.info(LOG_INFORMATION_GOT.formatted(entity));
        return VIDEO_ENTITY_MAPPER.entityToDto(entity);
    }
}
