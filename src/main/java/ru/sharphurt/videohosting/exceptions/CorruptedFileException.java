package ru.sharphurt.videohosting.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CorruptedFileException extends BaseException {

    public CorruptedFileException(String message) {
        super(EXCEPTION_MESSAGE_BASE.formatted(message));
    }
}
