package com.anghel.music_band_organizer.models.dtos.user;

import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.models.entities.Message;
import com.anghel.music_band_organizer.validations.regex.RegexPattern;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetAllUsersDTO {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 30, message = "Must be between 3 and 30 characters.")
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 30, message = "Must be between 3 and 30 characters.")
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 200, message = "must be between 3 and 30 characters")
    private String description;

    @NotBlank
    @RegexPattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Must be of format XXXX-XX-XX and not null.")
    private String birthday;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3, max = 30, message = "Must be between 3 and 30 characters.")
    private String stageName;

    private Map<String, String> pastExperience = new LinkedHashMap<>();

    private Map<String, String> bandRole = new LinkedHashMap<>();

    @JsonIgnore
    private List<Band> bandList = new ArrayList<>();

    @JsonIgnore
    private List<Message> toUserList = new ArrayList<>();

    @JsonIgnore
    private List<Message> fromUserList = new ArrayList<>();
}
