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
    private String filename;
    private Boolean processing;

    @JsonIgnore
    private Path path;

    @JsonIgnore
    private String extension;

    @Nullable
    private Boolean processingSuccess;
}
