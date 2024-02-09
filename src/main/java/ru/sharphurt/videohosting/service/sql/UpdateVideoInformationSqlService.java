package ru.sharphurt.videohosting.service.sql;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sharphurt.videohosting.exceptions.VideoNotFoundException;
import ru.sharphurt.videohosting.repository.VideoRepository;

import java.util.UUID;

import static ru.sharphurt.videohosting.constants.AliasConstants.LOG_INFORMATION_SAVED;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateVideoInformationSqlService {

    private final VideoRepository repository;

    private final String serviceName = "update-video-information-database-service";

    public void setCurrentProcessingStatus(UUID uuid, Boolean status) {
        var entity = repository.findById(uuid).orElseThrow(() -> new VideoNotFoundException(serviceName, uuid.toString()));
        entity.setProcessing(status);

        log.info(LOG_INFORMATION_SAVED.formatted(entity));
        repository.save(entity);
    }

    public void setLastProcessingResult(UUID uuid, Boolean result) {
        var entity = repository.findById(uuid).orElseThrow(() -> new VideoNotFoundException(serviceName, uuid.toString()));
        entity.setProcessingSuccess(result);

        log.info(LOG_INFORMATION_SAVED.formatted(entity));
        repository.save(entity);
    }
}
