package ru.sharphurt.videohosting.service.filesystem;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.sharphurt.videohosting.dto.VideoFileInformationDto;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public interface StorageService {

    VideoFileInformationDto save(MultipartFile file) throws IOException;
    
    Path getLocalFilePath(UUID uuid);

    MultipartFile load(Path path);

    Stream<Path> loadAll();
}
