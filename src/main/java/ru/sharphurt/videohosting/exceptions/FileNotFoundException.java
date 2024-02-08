package ru.sharphurt.videohosting.exceptions;

import lombok.extern.slf4j.Slf4j;

import static ru.sharphurt.videohosting.constants.AliasConstants.EXCEPTION_FILE_NOT_FOUND;

@Slf4j
public class FileNotFoundException extends BaseException {

    public FileNotFoundException(String methodName, String filename) {
        super(EXCEPTION_FILE_NOT_FOUND.formatted(methodName, filename));
    }
}
