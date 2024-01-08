package com.anghel.music_band_organizer.services.user;

import com.anghel.music_band_organizer.models.dtos.UserDTO;
import com.anghel.music_band_organizer.models.entities.User;
import com.anghel.music_band_organizer.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userServiceValidation.validateUserAlreadyExists(userDTO);

        User user = modelMapper.map(userDTO, User.class);
        user.setAge(calculateAge(user.getBirthday()));
        User savedUser = userRepository.save(user);
        log.info("User {} : {} inserted in db.", savedUser.getId(), savedUser.getEmail());

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }

    private static Integer calculateAge(LocalDate birthday) {
//        return (int)((LocalDate.now().toEpochDay() - birthday.toEpochDay()) / 365);
        return (int)(ChronoUnit.YEARS.between(birthday, LocalDate.now()));
    }
}
