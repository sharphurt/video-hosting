package ru.sharphurt.videohosting.exceptions;

import lombok.extern.slf4j.Slf4j;

import static ru.sharphurt.videohosting.constants.AliasConstants.EXCEPTION_FILE_NOT_SAVED;

@Slf4j
public class FileNotSavedException extends BaseException {

    public FileNotSavedException(String methodName, String filename) {
        super(EXCEPTION_FILE_NOT_SAVED.formatted(methodName, filename), new Throwable());
    }

    public FileNotSavedException(String methodName, String filename, Throwable e) {
        super(EXCEPTION_FILE_NOT_SAVED.formatted(methodName, filename), e);
    }
}
