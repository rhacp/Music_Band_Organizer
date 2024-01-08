package com.anghel.music_band_organizer.services.user;

import com.anghel.music_band_organizer.models.dtos.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();
}
