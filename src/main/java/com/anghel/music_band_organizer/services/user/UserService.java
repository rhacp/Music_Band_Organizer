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
     * Creates a user based on the given userDTO.
     * @param userDTO given userDTO
     * @return UserDTO of the saved user
     */
    UserDTO createUser(UserDTO userDTO);

    /**
     * Returns the list of all existing users.
     * @return List of UserDTO
     */
    List<GetAllUsersDTO> getAllUsers();

    /**
     * Return a user based on id.
     * @param userId user id to search for
     * @return UserDTO returned user DTO
     */
    UserDTO getUserById(Long userId);

    /**
     * Delete user by the given id.
     * @param userId user id to delete
     * @return String message
     */
    String deleteUserById(Long userId);

    /**
     * Update user by the given id and returns it.
     * @param userId
     * @param userDTO
     * @return UserDTO
     */
    UserDTO updateUserById(Long userId, UserDTO userDTO);

    /**
     * Returns a list of filtered users on different criteria.
     * @param userId user id for filtering
     * @param firstName user first name for filtering
     * @param lastName user last name for filtering
     * @param userEmail user email for filtering
     * @param userBirthday user birthday for filtering
     * @param pastExperience user past experience map key for filtering
     * @param userStageName user id for filtering
     * @return List of UserDTO
     */
    List<UserDTO> getFilteredUsers(Long userId,
                                   String firstName,
                                   String lastName,
                                   String userEmail,
                                   LocalDate userBirthday,
                                   String pastExperience,
                                   String userStageName);

    void checkUserForFinishRehearsal(Long userId, Rehearsal rehearsal, String methodName);

    User addUserToBand(Long userId, Long userToAddId, Band band, String methodName);

    User makeUserAdminInBand(Long userId, Long userToChangeRoleId, Band band, String methodName);

    void checkUserForCreateRehearsal(Long userId, String methodName, Band band);

    User sendMessage(Long userId, String methodName);

    void checkUserInBandForDeletePost(Long userId, Band band, String methodName);

    User makeUserOwnerForCreateBand(Long userId, String bandName, String methodName);

    User getUserForConversation(Long userId, String methodName);

    void checkUserAdminInBandForUpdateBand(Long userId, Band band, String methodName);

    User changeUserToMemberInBand(Long userId, Long userToChangeRoleId, Band band, String methodName);
}
