package com.anghel.music_band_organizer.services.user;

import com.anghel.music_band_organizer.models.dtos.user.UserDTO;
import com.anghel.music_band_organizer.models.entities.User;

public interface UserServiceValidation {

    void validateUserAlreadyExists(UserDTO userDTO);

    User getValidUser(Long userId, String methodName);
}
