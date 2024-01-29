package com.anghel.music_band_organizer.services.user;

import com.anghel.music_band_organizer.models.dtos.user.UserDTO;
import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.models.entities.User;

public interface UserServiceValidation {

    /**
     * Validates if a user with the same id as the given userDTO exists.
     * @param userDTO userDTO to get the id from
     * @exception com.anghel.music_band_organizer.exceptions.user.UserAlreadyExistsException if user exists
     */
    void validateUserAlreadyExists(UserDTO userDTO);

    /**
     * Search for a user with the specified id and returns it.
     * @param userId id to check
     * @param methodName methodName
     * @return User found user
     * @exception com.anghel.music_band_organizer.exceptions.user.UserNotFoundException if user not found
     */
    User getValidUser(Long userId, String methodName);

    /**
     * Check if the specified user is part of the specified band. In case it does not, throws exception.
     * @param user user to check
     * @param band band to check
     */
    void validateUserNotInSpecificBandException(User user, Band band);

    /**
     * Check if the specified user is ADMIN in the specified band. In case it isn't, throws exception.
     * @param user user to check
     * @param band band to check
     */
    void validateUserNotAdminInBandException(User user, Band band);

    /**
     * Check if the first user is the same as the second user. If it is, throws exception.
     * @param user user to check
     * @param secondUser second user to check
     */
    void validateUserDuplicateException(User user, User secondUser);

    /**
     * Check if the user is already part of the specified band. If it is, throws exception.
     * @param user user to check
     * @param band band to check
     */
    void validateUserAlreadyInSpecificBandException(User user, Band band);
}
