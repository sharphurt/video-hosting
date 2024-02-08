package ru.sharphurt.videohosting.service.filesystem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.sharphurt.videohosting.dto.VideoFileInformationDto;
import ru.sharphurt.videohosting.exceptions.CorruptedFileException;
import ru.sharphurt.videohosting.exceptions.FileNotSavedException;
import ru.sharphurt.videohosting.exceptions.UnacceptableFileTypeException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import static java.util.Objects.isNull;
import static ru.sharphurt.videohosting.constants.AliasConstants.LOG_FILE_SAVED;
import static ru.sharphurt.videohosting.constants.AliasConstants.LOG_FILE_SAVING;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaveToStorageService {

    @Value("${filestorage.upload-base-path}")
    private String baseFilepath;

    @Value("${filestorage.acceptable-filetype}")
    private String acceptableExtension;

    private final String serviceName = "save-to-storage-service";

    public VideoFileInformationDto save(MultipartFile file) {
        log.info(LOG_FILE_SAVING.formatted(file.getOriginalFilename()));

        var originalName = file.getOriginalFilename();
        var extension = FilenameUtils.getExtension(originalName);
        var filename = FilenameUtils.getBaseName(originalName);

        if (file.isEmpty()) {
            throw new CorruptedFileException(serviceName, originalName);
        }

        if (isNull(extension) || !acceptableExtension.equalsIgnoreCase(extension)) {
            throw new UnacceptableFileTypeException(serviceName, originalName);
        }

        var uuid = UUID.randomUUID();
        var path = getLocalFilePath(uuid);

        try {
            file.transferTo(path);
        } catch (IOException e) {
            throw new FileNotSavedException(serviceName, e.getMessage());
        }

        log.info(LOG_FILE_SAVED.formatted(uuid));

        return VideoFileInformationDto.builder().id(uuid).path(path).filename(filename).extension(extension).build();
    }

    public Path getLocalFilePath(UUID uuid) {
        return Path.of(baseFilepath, uuid + "." + acceptableExtension);
    }
}
