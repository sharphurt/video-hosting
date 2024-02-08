package ru.sharphurt.videohosting.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ru.sharphurt.videohosting.exceptions.CorruptedFileException;
import ru.sharphurt.videohosting.exceptions.UnacceptableFileTypeException;
import ru.sharphurt.videohosting.service.filesystem.SaveToStorageService;

import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sharphurt.videohosting.sample.MultipartFileSample.*;

@ExtendWith(MockitoExtension.class)
public class FileSystemStorageServiceTest {

    @InjectMocks
    private SaveToStorageService service;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(service, "baseFilepath", "/storage/videos/");
        ReflectionTestUtils.setField(service, "acceptableExtension", "mp4");
    }

    @Test
    @SneakyThrows
    public void saveFileTest_Success() {
        var result = service.save(multipartFile_correct);

        var savedFile = result.getPath().toFile();
        assertNotNull(savedFile);
        assertTrue(savedFile.exists());
        assertArrayEquals(sampleFileContent, Files.readAllBytes(result.getPath()));
    }

    @Test
    @SneakyThrows
    public void saveFileTest_EmptyFile() {
        assertThrows(CorruptedFileException.class, () -> {
            service.save(multipartFile_empty);
        });
    }

    @Test
    @SneakyThrows
    public void saveFileTest_UnsupportedExtension() {
        assertThrows(UnacceptableFileTypeException.class, () -> {
            service.save(multipartFile_unsupportedExtension);
        });
    }

    @Test
    @SneakyThrows
    public void saveFileTest_noExtension() {
        assertThrows(UnacceptableFileTypeException.class, () -> {
            service.save(multipartFile_noExtension);
        });
    }

    @Test
    @SneakyThrows
    public void saveFileTest_doubleExtension() {
        assertThrows(UnacceptableFileTypeException.class, () -> {
            service.save(multipartFile_doubleExtension);
        });
    }
}
