package ru.sharphurt.videohosting.exceptions;

import lombok.extern.slf4j.Slf4j;

import static ru.sharphurt.videohosting.constants.AliasConstants.EXCEPTION_CORRUPTED_FILE;

@Slf4j
public class CorruptedFileException extends BaseException {

    public CorruptedFileException(String methodName, String filename) {
        super(EXCEPTION_CORRUPTED_FILE.formatted(methodName, filename), new Throwable());
    }

    public CorruptedFileException(String methodName, String filename, Throwable e) {
        super(EXCEPTION_CORRUPTED_FILE.formatted(methodName, filename), e);
    }
}
