package ru.sharphurt.videohosting.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sharphurt.videohosting.dto.resp.ControllerErrorResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            VideoNotFoundException.class,
            FileNotFoundException.class
    })
    public ResponseEntity<ControllerErrorResponse> notFoundHandler(BaseException e) {
        return new ResponseEntity<>(ControllerErrorResponse.fromException(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {
            FileNotDeletedException.class,
            FileNotSavedException.class,
            VideoProcessingException.class,
            CorruptedFileException.class,
            BaseException.class
    })
    public ResponseEntity<ControllerErrorResponse> internalServerErrorHandler(BaseException e) {
        return new ResponseEntity<>(ControllerErrorResponse.fromException(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnacceptableFileTypeException.class)
    public ResponseEntity<ControllerErrorResponse> badRequestHandler(BaseException e) {
        return new ResponseEntity<>(ControllerErrorResponse.fromException(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ControllerErrorResponse> unknownException(RuntimeException e) {
        return new ResponseEntity<>(ControllerErrorResponse.fromException(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
