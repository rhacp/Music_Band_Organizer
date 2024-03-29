package com.anghel.music_band_organizer.exceptions;

import com.anghel.music_band_organizer.exceptions.band.BandAlreadyExistsException;
import com.anghel.music_band_organizer.exceptions.band.BandNotFoundException;
import com.anghel.music_band_organizer.exceptions.message.MessageAlreadyExistsException;
import com.anghel.music_band_organizer.exceptions.message.MessageNotFoundException;
import com.anghel.music_band_organizer.exceptions.open_ai.OpenAIException;
import com.anghel.music_band_organizer.exceptions.post.PostAlreadyExistsException;
import com.anghel.music_band_organizer.exceptions.post.PostNotFoundException;
import com.anghel.music_band_organizer.exceptions.rehearsal.RehearsalAlreadyExistsException;
import com.anghel.music_band_organizer.exceptions.rehearsal.RehearsalNotFoundException;
import com.anghel.music_band_organizer.exceptions.user.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, Object> result = new HashMap<>();

        exception.getBindingResult().getFieldErrors()
                .forEach(error -> result.put(error.getField(), error.getDefaultMessage()));

        log.info("MethodArgumentNotValidException thrown from DTO validation.");
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        log.info("UserAlreadyExistsException thrown.");
        return getResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        log.info("UserNotFoundException thrown.");
        return getResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotInSpecificBandException.class)
    public ResponseEntity<Object> handleUserNotInSpecificBandException(UserNotInSpecificBandException e) {
        log.info("UserNotInSpecificBandException thrown.");
        return getResponse(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotSpecificRoleInBandException.class)
    public ResponseEntity<Object> handleUserNotAdminInBandException(UserNotSpecificRoleInBandException e) {
        log.info("UserNotAdminInBandException thrown.");
        return getResponse(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserDuplicateException.class)
    public ResponseEntity<Object> handleUserDuplicateException(UserDuplicateException e) {
        log.info("UserNotAdminInBandException thrown.");
        return getResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyInSpecificBandException.class)
    public ResponseEntity<Object> handleUserAlreadyInSpecificBandException(UserAlreadyInSpecificBandException e) {
        log.info("UserAlreadyInSpecificBandException thrown.");
        return getResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BandAlreadyExistsException.class)
    public ResponseEntity<Object> handleBandAlreadyExistsException(BandAlreadyExistsException e) {
        log.info("BandAlreadyExistsException thrown.");
        return getResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BandNotFoundException.class)
    public ResponseEntity<Object> handleBandNotFoundException(BandNotFoundException e) {
        log.info("BandNotFoundException thrown.");
        return getResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RehearsalAlreadyExistsException.class)
    public ResponseEntity<Object> handleRehearsalAlreadyExistsException(RehearsalAlreadyExistsException e) {
        log.info("RehearsalAlreadyExistsException thrown.");
        return getResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RehearsalNotFoundException.class)
    public ResponseEntity<Object> handleRehearsalNotFoundException(RehearsalNotFoundException e) {
        log.info("RehearsalNotFoundException thrown.");
        return getResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MessageAlreadyExistsException.class)
    public ResponseEntity<Object> handleMessageAlreadyExistsException(MessageAlreadyExistsException e) {
        log.info("MessageAlreadyExistsException thrown.");
        return getResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<Object> handleMessageNotFoundException(MessageNotFoundException e) {
        log.info("MessageNotFoundException thrown.");
        return getResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostAlreadyExistsException.class)
    public ResponseEntity<Object> handlePostAlreadyExistsException(PostAlreadyExistsException e) {
        log.info("PostAlreadyExistsException thrown.");
        return getResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Object> handlePostNotFoundException(PostNotFoundException e) {
        log.info("PostNotFoundException thrown.");
        return getResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OpenAIException.class)
    public ResponseEntity<Object> handleOpenAIException(OpenAIException e) {
        log.info("OpenAIException thrown.");
        return getResponse(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> getResponse(RuntimeException e, HttpStatus httpStatus) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", e.getMessage());
        return new ResponseEntity<>(result, httpStatus);
    }
}
