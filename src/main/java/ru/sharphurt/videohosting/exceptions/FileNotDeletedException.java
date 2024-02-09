package ru.sharphurt.videohosting.exceptions;

import lombok.extern.slf4j.Slf4j;

import static ru.sharphurt.videohosting.constants.AliasConstants.EXCEPTION_FILE_NOT_DELETED;

@Slf4j
public class FileNotDeletedException extends BaseException {

    public FileNotDeletedException(String methodName, String filename) {
        super(EXCEPTION_FILE_NOT_DELETED.formatted(methodName, filename), new Throwable());
    }

    public FileNotDeletedException(String methodName, String filename, Throwable e) {
        super(EXCEPTION_FILE_NOT_DELETED.formatted(methodName, filename), e);
    }
}
