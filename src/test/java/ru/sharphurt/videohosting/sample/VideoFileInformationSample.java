package ru.sharphurt.videohosting.sample;

import ru.sharphurt.videohosting.dto.VideoFileInformationDto;
import ru.sharphurt.videohosting.dto.req.ResizeVideoRequestDto;

import java.util.UUID;

public class VideoFileInformationSample {

    public static VideoFileInformationDto videoFileInformationDto = VideoFileInformationDto.builder()
            .id(UUID.randomUUID())
            .filename("file")
            .extension("mp4")
            .build();

    public static ResizeVideoRequestDto resizeVideoRequestDto = ResizeVideoRequestDto.builder()
            .width(100)
            .height(100)
            .build();
}
