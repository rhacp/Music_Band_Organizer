package com.anghel.music_band_organizer.services.user;

import com.anghel.music_band_organizer.models.dtos.UserDTO;
import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.models.entities.Rehearsal;
import com.anghel.music_band_organizer.models.entities.User;
import com.anghel.music_band_organizer.repository.user.UserRepository;
import com.anghel.music_band_organizer.utils.enums.Role;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserServiceValidation userServiceValidation;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, UserServiceValidation userServiceValidation, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userServiceValidation = userServiceValidation;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userServiceValidation.validateUserAlreadyExists(userDTO);

        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);
        log.info("User {} : {} inserted in db. Method: {}", savedUser.getId(), savedUser.getEmail(), "createUser");

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Transactional
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        log.info("User list retrieved. Method: {}.", "getAllUsers");

        return userList.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }

    @Transactional
    @Override
    public UserDTO getUserById(Long userId) {
        User user = userServiceValidation.getValidUser(userId, "getUserById");

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public String deleteUserById(Long userId) {
        userServiceValidation.getValidUser(userId, "deleteUserById");

        userRepository.deleteById(userId);
        log.info("User with id {} deleted. Method: {}", userId, "deleteUserById");

        return "User with id " + userId + " deleted.";
    }

    @Override
    public List<UserDTO> getFilteredUsers(Long userId,
                                          String firstName,
                                          String lastName,
                                          String email,
                                          LocalDate birthday,
                                          String pastExperience,
                                          String stageName) {
        if (userId == null && firstName == null && lastName == null && email == null && birthday == null && pastExperience == null && stageName == null) {
            return userRepository.findAll().stream()
                    .map(user -> modelMapper.map(user, UserDTO.class))
                    .toList();
        }

        List<UserDTO> userDTOList = userRepository.findFilteredUser(userId, firstName, lastName, email, birthday, pastExperience, stageName).stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
        log.info("Filtered user list retrieved from db. Method {}.", "getFilteredUsers");


        if (pastExperience != null) {
            return userDTOList.stream()
                    .filter(userDTO -> userDTO.getPastExperience().containsKey(pastExperience))
                    .toList();
        }

        return userDTOList;
    }

    @Transactional
    @Override
    public User createBand(Long userId, String bandName) {
        User user = userServiceValidation.getValidUser(userId, "setUserBandAndRole");

        user.getBandRole().put(bandName, Role.ADMIN.getRoleLabel());
        User savedUser = userRepository.save(user);
        log.info("User {} : {} had a new band and role added in db. Method: {}", savedUser.getId(), savedUser.getEmail(), "addUserBandAndRole");

        return savedUser;
    }

    @Override
    public void finishRehearsal(Long userId, Rehearsal rehearsal) {
        User user = userServiceValidation.getValidUser(userId, "finishRehearsal");
        userServiceValidation.validateUserNotInSpecificBandException(user, rehearsal.getRehearsalBand());
    }

    @Transactional
    @Override
    public User addUserToBand(Long userId, Long userToAddId, Band band, String methodName) {
        User user = userServiceValidation.getValidUser(userId, methodName);
        User userToAdd = userServiceValidation.getValidUser(userToAddId, methodName);
        userServiceValidation.validateUserDuplicateException(user, userToAdd);

        userServiceValidation.validateUserNotInSpecificBandException(user, band);
        userServiceValidation.validateUserNotAdminInBandException(user, band);
        userServiceValidation.validateUserAlreadyInSpecificBandException(userToAdd, band);

        userToAdd.getBandList().add(band);
        userToAdd.getBandRole().put(band.getBandName(), Role.MEMBER.getRoleLabel());
        User savedUser = userRepository.save(userToAdd);
        log.info("User {} : {} had bandList and bandRole changed. Method: {}", savedUser.getId(), savedUser.getEmail(), "addUserToBand");

        return savedUser;
    }

    @Transactional
    @Override
    public User makeUserAdminInBand(Long userId, Long userToChangeRoleId, Band band, String methodName) {
        User user = userServiceValidation.getValidUser(userId, methodName);
        User userToChangeRole = userServiceValidation.getValidUser(userToChangeRoleId, methodName);
        userServiceValidation.validateUserDuplicateException(user, userToChangeRole);

        userServiceValidation.validateUserNotInSpecificBandException(user, band);
        userServiceValidation.validateUserNotAdminInBandException(user, band);
        userServiceValidation.validateUserNotInSpecificBandException(userToChangeRole, band);

        userToChangeRole.getBandRole().put(band.getBandName(), Role.ADMIN.getRoleLabel());
        User savedUser = userRepository.save(userToChangeRole);
        log.info("User {} : {} had bandList and bandRole changed. Method: {}", savedUser.getId(), savedUser.getEmail(), "addUserToBand");

        return savedUser;
    }

    @Override
    public User sendMessage(Long userId, String methodName) {
        return userServiceValidation.getValidUser(userId,methodName);
    }

    @Override
    public User createRehearsal(Long userId, String methodName, Band band) {
        User user = userServiceValidation.getValidUser(userId,methodName);
        userServiceValidation.validateUserNotAdminInBandException(user, band);

        return user;
    }

    @Override
    public void checkUserInBandForDeletePost(Long userId, Band band, String methodName) {
        User user = userServiceValidation.getValidUser(userId, methodName);
        userServiceValidation.validateUserNotAdminInBandException(user, band);
    }
}
