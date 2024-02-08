package ru.sharphurt.videohosting.dto;

import lombok.Builder;
import lombok.Data;

import javax.annotation.Nullable;
import java.nio.file.Path;
import java.util.UUID;

@Data
@Builder
public class VideoFileInformationDto {

    private UUID id;
    private Path path;
    private String filename;
    private String extension;

    private Boolean processing;

    @Nullable
    private Boolean processingSuccess;

    @Override
    public String toString() {
        return "VideoFileInformationDto {\n" +
                "   id: %s, \n".formatted(id) +
                "   path: %s, \n".formatted(path) +
                "   filename: %s, \n".formatted(filename) +
                "   extension: %s, \n".formatted(extension) +
                "   processing: %s, \n".formatted(processing) +
                "   processingSuccess: %s, \n".formatted(processingSuccess) +
                "}";
    }
}
