package ru.sharphurt.videohosting.dto.resp;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class VideoUploadResponseDto {

    private UUID id;
}