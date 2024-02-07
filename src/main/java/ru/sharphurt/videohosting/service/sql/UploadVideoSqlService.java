package ru.sharphurt.videohosting.service.sql;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sharphurt.videohosting.dto.VideoFileInformationDto;
import ru.sharphurt.videohosting.repository.VideoRepository;

import static ru.sharphurt.videohosting.mapper.UploadVideoInformationMapper.VIDEO_INFORMATION_TO_ENTITY_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadVideoSqlService {

    private final VideoRepository repository;

    public void uploadFileInformation(VideoFileInformationDto dto) {
        repository.save(VIDEO_INFORMATION_TO_ENTITY_MAPPER.dtoToEntity(dto));
    }
}
