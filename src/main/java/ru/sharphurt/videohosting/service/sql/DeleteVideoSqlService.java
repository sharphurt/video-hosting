package ru.sharphurt.videohosting.service.sql;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sharphurt.videohosting.exceptions.VideoNotFoundException;
import ru.sharphurt.videohosting.repository.VideoRepository;

import java.util.UUID;

import static ru.sharphurt.videohosting.constants.AliasConstants.LOG_INFORMATION_DELETED;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteVideoSqlService {

    private final VideoRepository repository;

    private final String serviceName = "delete-video-database-service";

    public void delete(UUID uuid) {
        var entity = repository.findById(uuid).orElseThrow(() -> new VideoNotFoundException(serviceName, uuid.toString()));
        repository.delete(entity);

        log.info(LOG_INFORMATION_DELETED.formatted(entity));
    }
}
