package com.anghel.music_band_organizer.exceptions;

import com.anghel.music_band_organizer.exceptions.band.BandAlreadyExistsException;
import com.anghel.music_band_organizer.exceptions.band.BandNotFoundException;
import com.anghel.music_band_organizer.exceptions.message.MessageAlreadyExistsException;
import com.anghel.music_band_organizer.exceptions.message.MessageNotFoundException;
import com.anghel.music_band_organizer.exceptions.post.PostAlreadyExistsException;
import com.anghel.music_band_organizer.exceptions.post.PostNotFoundException;
import com.anghel.music_band_organizer.exceptions.rehearsal.RehearsalAlreadyExistsException;
import com.anghel.music_band_organizer.exceptions.rehearsal.RehearsalNotFoundException;
import com.anghel.music_band_organizer.exceptions.user.UserAlreadyExistsException;
import com.anghel.music_band_organizer.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, Object> result = new HashMap<>();

        exception.getBindingResult().getFieldErrors()
                .forEach(error -> result.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return getResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        return getResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BandAlreadyExistsException.class)
    public ResponseEntity<Object> handleBandAlreadyExistsException(BandAlreadyExistsException e) {
        return getResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BandNotFoundException.class)
    public ResponseEntity<Object> handleBandNotFoundException(BandNotFoundException e) {
        return getResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RehearsalAlreadyExistsException.class)
    public ResponseEntity<Object> handleRehearsalAlreadyExistsException(RehearsalAlreadyExistsException e) {
        return getResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RehearsalNotFoundException.class)
    public ResponseEntity<Object> handleRehearsalNotFoundException(RehearsalNotFoundException e) {
        return getResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MessageAlreadyExistsException.class)
    public ResponseEntity<Object> handleMessageAlreadyExistsException(MessageAlreadyExistsException e) {
        return getResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<Object> handleMessageNotFoundException(MessageNotFoundException e) {
        return getResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostAlreadyExistsException.class)
    public ResponseEntity<Object> handlePostAlreadyExistsException(PostAlreadyExistsException e) {
        return getResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Object> handlePostNotFoundException(PostNotFoundException e) {
        return getResponse(e, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> getResponse(RuntimeException e, HttpStatus httpStatus) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", e.getMessage());
        return new ResponseEntity<>(result, httpStatus);
    }
}
