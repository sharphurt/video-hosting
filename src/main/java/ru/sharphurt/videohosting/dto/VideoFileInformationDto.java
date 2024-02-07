package ru.sharphurt.videohosting.dto;

import lombok.Builder;
import lombok.Data;

import java.nio.file.Path;
import java.util.UUID;

@Data
@Builder
public class VideoFileInformationDto {

    private UUID id;
    private Path path;
    private String filename;
    private String extension;
}
