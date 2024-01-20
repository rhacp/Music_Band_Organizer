package com.anghel.music_band_organizer.services.user;

import com.anghel.music_band_organizer.models.dtos.user.UserDTO;
import com.anghel.music_band_organizer.models.dtos.user.UserFilterDTO;
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
        log.info("User list retrieved. Method: {}", "getAllUsers");

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
    public User addUserBandAndRole(Long userId, String bandName) {
        User user = userServiceValidation.getValidUser(userId, "setUserBandAndRole");

        user.getBandRole().put(Role.ADMIN.getRoleLabel(), bandName);
        User savedUser = userRepository.save(user);
        log.info("User {} : {} had a new band and role added in db. Method: {}", savedUser.getId(), savedUser.getEmail(), "addUserBandAndRole");

        return savedUser;
    }

    //    private static Integer calculateAge(LocalDate birthday) {
//        return (int)(ChronoUnit.YEARS.between(birthday, LocalDate.now()));
//    }
}
