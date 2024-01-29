package com.anghel.music_band_organizer.models.dtos.post;

import com.anghel.music_band_organizer.models.entities.Band;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GetAllPostsDTO {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 30, message = "Must be between 3 and 30 characters.")
    private String postTitle;

    @Size(min = 0, max = 1000, message = "Must be between 3 and 5000 characters.")
    private String postDescription;

    @NotBlank
    @Size(min = 3, max = 30, message = "Must be between 3 and 30 characters.")
    private String postGenre;

    @NotBlank
    @Size(min = 3, max = 30, message = "Must be between 3 and 30 characters.")
    private String postRecordingTitle;

    @NotNull
    private Boolean useOpenAIForDescription;

    @JsonIgnore
    private Band postBand;
}
