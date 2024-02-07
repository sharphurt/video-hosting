package ru.sharphurt.videohosting.exceptions;

public class UnacceptableFileTypeException extends BaseException {

    public UnacceptableFileTypeException(String message) {
        super(EXCEPTION_MESSAGE_BASE.formatted(message));
    }
}
