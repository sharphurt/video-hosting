package ru.sharphurt.videohosting.exceptions;

import static ru.sharphurt.videohosting.constants.AliasConstants.EXCEPTION_UNACCEPTABLE_TYPE;

public class UnacceptableFileTypeException extends BaseException {

    public UnacceptableFileTypeException(String methodName, String filename) {
        super(EXCEPTION_UNACCEPTABLE_TYPE.formatted(methodName, filename));
    }
}
