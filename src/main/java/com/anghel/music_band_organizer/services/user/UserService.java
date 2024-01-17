package com.anghel.music_band_organizer.services.user;

import com.anghel.music_band_organizer.models.dtos.user.UserDTO;
import com.anghel.music_band_organizer.models.dtos.user.UserFilterDTO;
import com.anghel.music_band_organizer.models.entities.User;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long userId);

    String deleteUserById(Long userId);

    List<UserDTO> getFilteredUsers(UserFilterDTO userFilterDTO);
}
