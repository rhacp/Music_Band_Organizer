package com.anghel.music_band_organizer.services.user;

import com.anghel.music_band_organizer.models.dtos.user.GetAllUsersDTO;
import com.anghel.music_band_organizer.models.dtos.user.UserDTO;
import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.models.entities.Rehearsal;
import com.anghel.music_band_organizer.models.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    /**
     * Creates a User based on DTO.
     * @param userDTO
     * @return UserDTO of the saved user
     */
    UserDTO createUser(UserDTO userDTO);

    /**
     * Returns all users from DB.
     * @return UserDTO List
     */
    List<GetAllUsersDTO> getAllUsers();

    UserDTO getUserById(Long userId);

    String deleteUserById(Long userId);

    UserDTO updateUserById(Long userId, UserDTO userDTO);

    List<UserDTO> getFilteredUsers(Long userId,
                                   String firstName,
                                   String lastName,
                                   String userEmail,
                                   LocalDate userBirthday,
                                   String pastExperience,
                                   String userStageName);

    void finishRehearsal(Long userId, Rehearsal rehearsal);

    User addUserToBand(Long userId, Long userToAddId, Band band, String methodName);

    User makeUserAdminInBand(Long userId, Long userToChangeRoleId, Band band, String methodName);

    User createRehearsal(Long userId, String methodName, Band band);

    User sendMessage(Long userId, String methodName);

    void checkUserInBandForDeletePost(Long userId, Band band, String methodName);

    User makeUserAdminForCreateBand(Long userId, String bandName, String methodName);

    User getUserForConversation(Long userId, String methodName);

    void checkUserAdminInBandForUpdateBand(Long userId, Band band, String methodName);

    User changeUserToMemberInBand(Long userId, Long userToChangeRoleId, Band band, String methodName);
}
