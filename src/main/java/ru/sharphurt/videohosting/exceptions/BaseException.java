package ru.sharphurt.videohosting.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class BaseException extends RuntimeException {

    public static final String EXCEPTION_MESSAGE_BASE = "Exception during request execution: %s";

    public String message;

    public BaseException(String message) {
        super(message);
        log.error(message);
        this.message = message;
    }
}
