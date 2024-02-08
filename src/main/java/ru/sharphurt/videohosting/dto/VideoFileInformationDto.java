package ru.sharphurt.videohosting.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.annotation.Nullable;
import java.nio.file.Path;
import java.util.UUID;

@Data
@Builder
public class VideoFileInformationDto {

    private UUID id;

    @JsonIgnore
    private Path path;

    @JsonIgnore
    private String extension;

    private String filename;

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
