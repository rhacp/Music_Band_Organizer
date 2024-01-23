package com.anghel.music_band_organizer.services.user;

import com.anghel.music_band_organizer.models.dtos.UserDTO;
import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.models.entities.Rehearsal;
import com.anghel.music_band_organizer.models.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long userId);

    String deleteUserById(Long userId);

    List<UserDTO> getFilteredUsers(Long userId,
                                   String firstName,
                                   String lastName,
                                   String userEmail,
                                   LocalDate userBirthday,
                                   String pastExperience,
                                   String userStageName);

    User createBand(Long userId, String bandName);

    void finishRehearsal(Long userId, Rehearsal rehearsal);

    User addUserToBand(Long userId, Long userToAddId, Band band, String methodName);

    User makeUserAdminInBand(Long userId, Long userToChangeRoleId, Band band, String methodName);
}
