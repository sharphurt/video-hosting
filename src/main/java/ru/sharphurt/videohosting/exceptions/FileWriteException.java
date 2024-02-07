package ru.sharphurt.videohosting.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileWriteException extends BaseException {

    public FileWriteException(String message) {
        super(EXCEPTION_MESSAGE_BASE.formatted(message));
    }
}
