package com.anghel.music_band_organizer.models.dtos.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserFilterDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDateTime birthday;

    private String email;

    private String stageName;

    private String pastExperience;
}
