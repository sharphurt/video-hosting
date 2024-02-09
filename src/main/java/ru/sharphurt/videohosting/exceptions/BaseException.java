package ru.sharphurt.videohosting.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class BaseException extends RuntimeException {

    private final String message;

    public BaseException(String message, Throwable e) {
        super(message);
        log.error(message, e);
        this.message = message;
    }
}
