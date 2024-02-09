package ru.sharphurt.videohosting.sample;

import ru.sharphurt.videohosting.dto.req.ResizeVideoRequestDto;

public class ResizeVideoDtoSample {

    public static ResizeVideoRequestDto resizeVideoRequestDto = ResizeVideoRequestDto.builder()
            .width(100)
            .height(100)
            .build();

    public static ResizeVideoRequestDto resizeVideoRequestDto_smallWidth = ResizeVideoRequestDto.builder()
            .width(10)
            .height(100)
            .build();

    public static ResizeVideoRequestDto resizeVideoRequestDto_smallHeight = ResizeVideoRequestDto.builder()
            .width(100)
            .height(10)
            .build();

    public static ResizeVideoRequestDto resizeVideoRequestDto_notEvenWidth = ResizeVideoRequestDto.builder()
            .width(89)
            .height(100)
            .build();

    public static ResizeVideoRequestDto resizeVideoRequestDto_notEvenHeight = ResizeVideoRequestDto.builder()
            .width(100)
            .height(89)
            .build();
}
