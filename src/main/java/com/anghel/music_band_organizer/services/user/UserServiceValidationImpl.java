package com.anghel.music_band_organizer.services.user;

import com.anghel.music_band_organizer.models.dtos.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceValidationImpl implements UserServiceValidation{

    @Override
    public void validateUserAlreadyExists(UserDTO userDTO) {

    }
}
