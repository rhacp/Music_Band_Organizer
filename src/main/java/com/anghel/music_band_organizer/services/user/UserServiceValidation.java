package com.anghel.music_band_organizer.services.user;

import com.anghel.music_band_organizer.models.dtos.UserDTO;

public interface UserServiceValidation {

    void validateUserAlreadyExists(UserDTO userDTO);
}
