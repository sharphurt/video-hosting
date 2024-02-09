package ru.sharphurt.videohosting.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sharphurt.videohosting.dto.resp.ControllerErrorResponse;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ControllerErrorResponse> internalServerErrorHandler(BaseException e) {
        return new ResponseEntity<>(ControllerErrorResponse.fromException(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {VideoNotFoundException.class, FileNotFoundException.class})
    public ResponseEntity<ControllerErrorResponse> notFoundHandler(BaseException e) {
        return new ResponseEntity<>(ControllerErrorResponse.fromException(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UnacceptableFileTypeException.class, CorruptedFileException.class})
    public ResponseEntity<ControllerErrorResponse> badRequestHandler(BaseException e) {
        return new ResponseEntity<>(ControllerErrorResponse.fromException(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ControllerErrorResponse> badRequestHandler(MethodArgumentNotValidException e) {
        var message = getValidationMessages(e);
        var throwable = new Throwable(message, e);
        return new ResponseEntity<>(ControllerErrorResponse.fromException(throwable), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ControllerErrorResponse> unknownException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(ControllerErrorResponse.fromException(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getValidationMessages(MethodArgumentNotValidException e) {
        var messages = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(" | "));

        return "Validation errors: " + messages;
    }
}
