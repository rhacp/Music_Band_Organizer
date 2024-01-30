package com.anghel.music_band_organizer.integration_test;

import com.anghel.music_band_organizer.models.dtos.user.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@Transactional
@AutoConfigureTestDatabase
@ActiveProfiles("RHACP")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_CreateUser_ShouldPass() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .birthday("1996-06-11")
                .email("example@gmail.com")
                .stageName("Death")
                .description("Hi!")
                .pastExperience(new HashMap<>())
                .build();
        userDTO.getPastExperience().put("piano", "3 years");

        MvcResult result = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andReturn();

        String resultAsString = result.getResponse().getContentAsString();
        UserDTO userDTOConverted = objectMapper.readValue(resultAsString, UserDTO.class);

        log.info("UserDTO result: " + userDTOConverted);

        assertEquals(userDTO.getFirstName(), userDTOConverted.getFirstName());
        assertEquals(userDTO.getLastName(), userDTOConverted.getLastName());
        assertEquals(userDTO.getBirthday(), userDTOConverted.getBirthday());
        assertEquals(userDTO.getEmail(), userDTOConverted.getEmail());
        assertEquals(userDTO.getStageName(), userDTOConverted.getStageName());
        assertEquals(userDTO.getDescription(), userDTOConverted.getDescription());
        assertEquals(userDTO.getPastExperience(), userDTOConverted.getPastExperience());
    }

    @Test
    void test_CreateUser_ShouldFail_IfPatientWithSameEmailAlreadyExists() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .birthday("1996-06-11")
                .email("example@gmail.com")
                .stageName("Death")
                .description("Hi!")
                .pastExperience(new HashMap<>())
                .build();
        userDTO.getPastExperience().put("piano", "3 years");

        UserDTO secondUserDTO = UserDTO.builder()
                .firstName("Lil")
                .lastName("Peep")
                .birthday("1998-08-12")
                .email("example@gmail.com")
                .stageName("The devil")
                .description("Hi!")
                .pastExperience(new HashMap<>())
                .build();
        secondUserDTO.getPastExperience().put("piano", "3 years");

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)));

        MvcResult result = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isConflict())
                .andReturn();

        String resultAsString = result.getResponse().getContentAsString();
        log.info("Result as String: " + resultAsString);

        assertTrue(resultAsString.contains("A user with the mail " + secondUserDTO.getEmail() + " already exists."));
    }
}
