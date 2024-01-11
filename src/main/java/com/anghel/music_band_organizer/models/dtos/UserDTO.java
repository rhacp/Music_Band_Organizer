package com.anghel.music_band_organizer.models.dtos;

import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.utils.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class UserDTO {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 30, message = "must be between 3 and 30 characters")
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 30, message = "must be between 3 and 30 characters")
    private String lastName;

    @NotNull
    private LocalDate birthday;

    private Integer age;

    private Role role;

    @NotBlank
    @Email
    private String email;

    private Map<String, String> pastExperience = new LinkedHashMap<>();

    private List<Band> bandList = new ArrayList<>();

    private Map<String, String> bandRole = new LinkedHashMap<>();
}
