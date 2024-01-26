package com.anghel.music_band_organizer.services.user;

import com.anghel.music_band_organizer.exceptions.user.*;
import com.anghel.music_band_organizer.models.dtos.UserDTO;
import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.models.entities.User;
import com.anghel.music_band_organizer.repository.user.UserRepository;
import com.anghel.music_band_organizer.utils.enums.Role;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class UserServiceValidationImpl implements UserServiceValidation {

    private final UserRepository userRepository;

    public UserServiceValidationImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void validateUserAlreadyExists(UserDTO userDTO) {
        User userFound = userRepository.findUserByEmail(userDTO.getEmail());

        if (userFound != null) {
            throw new UserAlreadyExistsException("A user with the mail " + userDTO.getEmail() + " already exists.");
        }
    }

    @Transactional
    @Override
    public User getValidUser(Long userId, String methodName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with the id " + userId + " not found."));
        log.info("User with the id {} retrieved. Method: {}.", userId, methodName);

        return user;
    }

    @Override
    public void validateUserNotInSpecificBandException(User user, Band band) {
        if (!user.getBandList().contains(band)) {
            throw new UserNotInSpecificBandException("User with id " + user.getId() + " not part of the band " + band.getBandName() + " with id " + band.getId() + ".");
        }
    }

    @Override
    public void validateUserNotAdminInBandException(User user, Band band) {
        if (!user.getBandRole().containsValue(Role.ADMIN.getRoleLabel())) {
            throw new UserNotAdminInBandException("User with id " + user.getId() + " not ADMIN in band " + band.getBandName() + ".");
        }

        if (!user.getBandRole().get(band.getBandName()).equals(Role.ADMIN.getRoleLabel())) {
            throw new UserNotAdminInBandException("User with id " + user.getId() + " not ADMIN in band " + band.getBandName() + ".");
        }
    }

    @Override
    public void validateUserDuplicateException(User user, User secondUser) {
        if (user.equals(secondUser)) {
            throw new UserDuplicateException("Users are the same.");
        }
    }

    @Override
    public void validateUserAlreadyInSpecificBandException(User user, Band band) {
        if (user.getBandList().contains(band)) {
            throw new UserAlreadyInSpecificBandException("User with id " + user.getId() + " already member of band " + band.getId() + ".");
        }
    }
}
