package ru.sharphurt.videohosting.service.filesystem.impl;

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
import ru.sharphurt.videohosting.service.filesystem.StorageService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileSystemStorageServiceImpl implements StorageService {

    @Value("${filestorage.upload-base-path}")
    private String baseFilepath;

    @Value("${filestorage.acceptable-filetype}")
    private String acceptableExtension;

    private final String serviceName = "file-system-service";

    @Override
    public VideoFileInformationDto save(MultipartFile file) {
        log.info("File %s start saving".formatted(file.getOriginalFilename()));

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

        log.info("File %s saved with UUID: %s".formatted(originalName, uuid));

        return VideoFileInformationDto.builder().id(uuid).path(path).filename(filename).extension(extension).build();
    }

    @Override
    public Path getLocalFilePath(UUID uuid) {
        return Path.of(baseFilepath, uuid + "." + acceptableExtension);
    }

    @Override
    public MultipartFile load(Path path) {
        return null;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }
}
