package com.anghel.music_band_organizer.services.user;

import com.anghel.music_band_organizer.exceptions.user.UserAlreadyExistsException;
import com.anghel.music_band_organizer.exceptions.user.UserNotFoundException;
import com.anghel.music_band_organizer.models.dtos.user.UserDTO;
import com.anghel.music_band_organizer.models.entities.User;
import com.anghel.music_band_organizer.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserServiceValidationImpl implements UserServiceValidation{

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
}
