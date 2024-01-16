package com.anghel.music_band_organizer.models.dtos;

import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.utils.enums.State;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RehearsalDTO {

    private Long id;

    private State state;

    @NotNull
    private LocalDateTime rehearsalTime;

    @NotNull
    @Min(1)
    @Max(9)
    private Integer rehearsalDurationHours;

    private Band rehearsalBand;
}
