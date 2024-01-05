package com.anghel.music_band_organizer.exceptions.user;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) { super (message); }
}
