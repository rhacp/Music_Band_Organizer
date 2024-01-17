package com.anghel.music_band_organizer.services.user;

import com.anghel.music_band_organizer.models.dtos.UserDTO;
import com.anghel.music_band_organizer.models.entities.User;
import com.anghel.music_band_organizer.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

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

    @Transactional
    @Override
    public String deleteUserById(Long userId) {
        userServiceValidation.getValidUser(userId, "deleteUserById");

        userRepository.deleteById(userId);
        log.info("User with id {} deleted. Method: {}", userId, "deleteUserById");

        return "User with id " + userId + " deleted.";
    }

//    private static Integer calculateAge(LocalDate birthday) {
//        return (int)(ChronoUnit.YEARS.between(birthday, LocalDate.now()));
//    }
}
