package com.anghel.music_band_organizer.services.user;

import com.anghel.music_band_organizer.models.dtos.user.UserDTO;
import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.models.entities.User;

public interface UserServiceValidation {

    void validateUserAlreadyExists(UserDTO userDTO);

    User getValidUser(Long userId, String methodName);

    void validateUserNotInSpecificBandException(User user, Band band);

    void validateUserNotAdminInBandException(User user, Band band);

    void validateUserDuplicateException(User user, User secondUser);

    void validateUserAlreadyInSpecificBandException(User user, Band band);
}
