package com.anghel.music_band_organizer.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MessageDTO {

    private Long id;

    private String fromUser;

    private String toUser;

    @NotBlank
    @Size(min = 3, max = 1000, message = "must be between 3 and 1000 characters")
    private String message;
}
