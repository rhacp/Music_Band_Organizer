package com.anghel.music_band_organizer.models.dtos.rehearsal;

import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.utils.enums.State;
import com.anghel.music_band_organizer.validations.availability.EnumAvailabilityPattern;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RehearsalDTO {

    private Long id;

    private State state;

    @NotNull
    private LocalDate rehearsalDate;

    @NotNull
    private LocalTime rehearsalTime;

    @EnumAvailabilityPattern(anyOf = {"private", "public"})
    private String rehearsalAvailability;

    @NotNull
    @Min(1)
    @Max(9)
    private Integer rehearsalDurationHours;

    private Band rehearsalBand;
}
