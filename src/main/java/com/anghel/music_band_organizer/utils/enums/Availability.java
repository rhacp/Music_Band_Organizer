package com.anghel.music_band_organizer.utils.enums;

import lombok.Getter;

@Getter
public enum Availability {

    PUBLIC("public"),
    PRIVATE("private");

    private final String availabilityLabel;

    Availability(String availabilityLabel) { this.availabilityLabel = availabilityLabel ;}
}
