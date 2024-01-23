package com.anghel.music_band_organizer.exceptions.user;

public class UserNotAdminInBandException extends RuntimeException{

    public UserNotAdminInBandException(String message) { super (message); }
}
