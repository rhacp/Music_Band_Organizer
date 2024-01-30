package com.anghel.music_band_organizer.unit_test;

import com.anghel.music_band_organizer.models.dtos.user.GetAllUsersDTO;
import com.anghel.music_band_organizer.models.dtos.user.UserDTO;
import com.anghel.music_band_organizer.models.entities.User;
import com.anghel.music_band_organizer.repository.user.UserRepository;
import com.anghel.music_band_organizer.services.user.UserServiceImpl;
import com.anghel.music_band_organizer.services.user.UserServiceValidationImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private final ModelMapper modelMapper = new ModelMapper();

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserServiceValidationImpl userServiceValidation;

    @Test
    void test_CreateUser_ShouldPass() {
        //GIVEN
        User user = new User();
        user.setFirstName("Crin");
        user.setLastName("Anton");
        user.setEmail("crin@gmail.com");
        user.setBirthday(LocalDate.parse("1998-03-11"));

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setFirstName("Crin");
        savedUser.setLastName("Anton");
        savedUser.setEmail("crin@gmail.com");
        savedUser.setBirthday(LocalDate.parse("1998-03-11"));

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName("Maria");
        userDTO.setLastName("Anton");
        userDTO.setEmail("crin@gmail.com");
        userDTO.setBirthday("1998-03-11");

        UserDTO returnedUserDTO = new UserDTO();
        returnedUserDTO.setId(1L);
        returnedUserDTO.setFirstName("Maria");
        returnedUserDTO.setLastName("Anton");
        returnedUserDTO.setEmail("crin@gmail.com");
        returnedUserDTO.setBirthday("1998-03-11");

        when(modelMapper.map(returnedUserDTO, User.class)).thenReturn(user);
        when(modelMapper.map(savedUser, UserDTO.class)).thenReturn(returnedUserDTO);
        when(userRepository.save(user)).thenReturn(savedUser);

        //WHEN
        UserDTO savedUserDTO = userService.createUser(returnedUserDTO);

        //THEN
        verify(userRepository, times(1)).save(user);
        assertEquals(returnedUserDTO, savedUserDTO);
    }

    @Test
    void test_GetAllUsers_ShouldPass() {
        //GIVEN
        GetAllUsersDTO firstExpectedUserDTO = GetAllUsersDTO.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .birthday("1996-06-11")
                .email("example@gmail.com")
                .stageName("Death")
                .description("Hi!")
                .pastExperience(new HashMap<>())
                .build();
        firstExpectedUserDTO.getPastExperience().put("piano", "3 years");

        GetAllUsersDTO secondExpectedUserDTO = GetAllUsersDTO.builder()
                .id(2L)
                .firstName("Lil")
                .lastName("Peep")
                .birthday("1998-08-12")
                .email("example@gmail.com")
                .stageName("The devil")
                .description("Hi!")
                .pastExperience(new HashMap<>())
                .build();
        secondExpectedUserDTO.getPastExperience().put("guitar", "2 years");

        User firstUser = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .birthday(LocalDate.parse("1996-06-11"))
                .email("example@gmail.com")
                .stageName("Death")
                .description("Hi!")
                .pastExperience(new HashMap<>())
                .build();
        firstUser.getPastExperience().put("piano", "3 years");

        User secondUser = User.builder()
                .id(2L)
                .firstName("Lil")
                .lastName("Peep")
                .birthday(LocalDate.parse("1998-08-12"))
                .email("example@gmail.com")
                .stageName("The devil")
                .description("Hi!")
                .pastExperience(new HashMap<>())
                .build();
        secondUser.getPastExperience().put("guitar", "2 years");

        List<GetAllUsersDTO> expectedUserDTOList = new ArrayList<>(List.of(firstExpectedUserDTO, secondExpectedUserDTO));
        List<User> userList = new ArrayList<>(List.of(firstUser, secondUser));

        when(userRepository.findAll()).thenReturn(userList);
        when(modelMapper.map(firstUser, GetAllUsersDTO.class)).thenReturn(firstExpectedUserDTO);
        when(modelMapper.map(secondUser, GetAllUsersDTO.class)).thenReturn(secondExpectedUserDTO);

        //WHEN
        List<GetAllUsersDTO> returnedUserDTOList = userService.getAllUsers();

        //THEN
        assertEquals(expectedUserDTOList, returnedUserDTOList);
    }
}
