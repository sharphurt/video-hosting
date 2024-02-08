package ru.sharphurt.videohosting.service.sql;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sharphurt.videohosting.dto.VideoFileInformationDto;
import ru.sharphurt.videohosting.exceptions.VideoNotFoundException;
import ru.sharphurt.videohosting.repository.VideoRepository;

import java.util.UUID;

import static ru.sharphurt.videohosting.mapper.UploadVideoInformationMapper.VIDEO_INFORMATION_TO_ENTITY_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoadVideoInformationSqlService {

    private final VideoRepository repository;

    private final String serviceName = "load-video-information-database-service";

    public VideoFileInformationDto loadVideoInformation(UUID uuid) {
        var entity = repository.findById(uuid).orElseThrow(() -> new VideoNotFoundException(serviceName, uuid.toString()));
        return VIDEO_INFORMATION_TO_ENTITY_MAPPER.entityToDto(entity);
    }
}
