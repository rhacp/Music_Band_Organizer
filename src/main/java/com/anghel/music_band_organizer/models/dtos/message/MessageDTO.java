package com.anghel.music_band_organizer.models.dtos.message;

import com.anghel.music_band_organizer.models.entities.User;
import com.anghel.music_band_organizer.validations.regex.RegexPattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MessageDTO {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 1000, message = "must be between 3 and 1000 characters")
    private String content;

    @RegexPattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Must be of format XXXX-XX-XX and not null.")
    private String messageDate;

    @RegexPattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "Must be of format XX.XX and not null.")
    private String messageTime;

    private User fromUser;

    private User toUser;
}