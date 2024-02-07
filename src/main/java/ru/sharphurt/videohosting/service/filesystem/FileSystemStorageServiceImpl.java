package ru.sharphurt.videohosting.service.filesystem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.sharphurt.videohosting.dto.VideoFileInformationDto;
import ru.sharphurt.videohosting.exceptions.CorruptedFileException;
import ru.sharphurt.videohosting.exceptions.FileWriteException;
import ru.sharphurt.videohosting.exceptions.UnacceptableFileTypeException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileSystemStorageServiceImpl implements StorageService {

    @Value("${filestorage.upload-base-path}")
    private String baseFilepath;

    @Value("${filestorage.acceptable-filetypes}")
    private List<String> acceptableExtensions;

    @Override
    public VideoFileInformationDto save(MultipartFile file) {
        log.info("File %s start saving".formatted(file.getOriginalFilename()));

        if (file.isEmpty()) {
            throw new CorruptedFileException("File is empty");
        }

        var extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        var filename = StringUtils.getFilename(file.getOriginalFilename());

        if (isNull(extension) || !acceptableExtensions.contains(extension.toLowerCase(Locale.ROOT))) {
            throw new UnacceptableFileTypeException("%s extension is not supported".formatted(extension));
        }

        var uuid = UUID.randomUUID();
        var path = Path.of(baseFilepath, uuid + "." + extension.toLowerCase(Locale.ROOT));

        try {
            file.transferTo(path);
        } catch (IOException e) {
            throw new FileWriteException(e.getMessage());
        }

        log.info("File %s saved with UUID: %s".formatted(file.getOriginalFilename(), uuid));

        return VideoFileInformationDto.builder().id(uuid).path(path).filename(filename).extension(extension).build();
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
