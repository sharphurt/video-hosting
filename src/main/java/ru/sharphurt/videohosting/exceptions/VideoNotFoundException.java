package ru.sharphurt.videohosting.exceptions;

import lombok.extern.slf4j.Slf4j;

import static ru.sharphurt.videohosting.constants.AliasConstants.EXCEPTION_VIDEO_NOT_FOUND;

@Slf4j
public class VideoNotFoundException extends BaseException {

    public VideoNotFoundException(String methodName, String filename) {
        super(EXCEPTION_VIDEO_NOT_FOUND.formatted(methodName, filename), new Throwable());
    }

    public VideoNotFoundException(String methodName, String filename, Throwable e) {
        super(EXCEPTION_VIDEO_NOT_FOUND.formatted(methodName, filename), e);
    }
}
