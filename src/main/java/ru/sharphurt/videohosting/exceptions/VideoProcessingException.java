package ru.sharphurt.videohosting.exceptions;

import lombok.extern.slf4j.Slf4j;

import static ru.sharphurt.videohosting.constants.AliasConstants.EXCEPTION_VIDEO_PROCESSING;

@Slf4j
public class VideoProcessingException extends BaseException {

    public VideoProcessingException(String methodName, String filename) {
        super(EXCEPTION_VIDEO_PROCESSING.formatted(methodName, filename));
    }
}
