package ru.sharphurt.videohosting.service.filesystem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sharphurt.videohosting.dto.VideoFileInformationDto;
import ru.sharphurt.videohosting.exceptions.FileNotDeletedException;
import ru.sharphurt.videohosting.exceptions.FileNotFoundException;

import java.io.IOException;
import java.nio.file.Files;

import static ru.sharphurt.videohosting.constants.AliasConstants.LOG_FILE_DELETE;
import static ru.sharphurt.videohosting.constants.AliasConstants.LOG_FILE_DELETED;

@Slf4j
@Service
public class DeleteFromStorageService {

    private final String serviceName = "delete-from-storage-service";

    public void delete(VideoFileInformationDto info) {
        log.info(LOG_FILE_DELETE.formatted(info.getFilename()));

        if (!Files.exists(info.getPath())) {
            throw new FileNotFoundException(serviceName, info.getPath().toString());
        }

        try {
            Files.delete(info.getPath());
        } catch (IOException e) {
            throw new FileNotDeletedException(serviceName, info.getPath().toString());
        }

        log.info(LOG_FILE_DELETED.formatted(info.getId()));
    }
}
