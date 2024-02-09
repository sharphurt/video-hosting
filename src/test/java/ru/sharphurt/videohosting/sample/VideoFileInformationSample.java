package ru.sharphurt.videohosting.sample;

import ru.sharphurt.videohosting.dto.VideoFileInformationDto;

import java.util.UUID;

public class VideoFileInformationSample {

    public static VideoFileInformationDto videoFileInformationDto = VideoFileInformationDto.builder()
            .id(UUID.randomUUID())
            .filename("correctfile")
            .extension("mp4")
            .build();
}
