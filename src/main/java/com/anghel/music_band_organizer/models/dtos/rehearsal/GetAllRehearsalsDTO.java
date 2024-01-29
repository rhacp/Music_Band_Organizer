package com.anghel.music_band_organizer.models.dtos.rehearsal;

import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.utils.enums.State;
import com.anghel.music_band_organizer.validations.availability.EnumAvailabilityPattern;
import com.anghel.music_band_organizer.validations.regex.RegexPattern;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetAllRehearsalsDTO {

    private Long id;

    private State state;

    @NotBlank
    @RegexPattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Must be of format XXXX-XX-XX and not null.")
    private String rehearsalDate;

    @NotBlank
    @RegexPattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "Must be of format XX.XX and not null.")
    private String rehearsalTime;

    @NotNull
    @Min(1)
    @Max(9)
    private Integer rehearsalDurationHours;

    @NotBlank
    @EnumAvailabilityPattern(anyOf = {"private", "public"})
    private String rehearsalAvailability;

    @JsonIgnore
    private Band rehearsalBand;
}
