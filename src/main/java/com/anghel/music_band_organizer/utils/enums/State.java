package com.anghel.music_band_organizer.utils.enums;

import lombok.Getter;

@Getter
public enum State {

    DUE("due"),
    DONE("done");

    private final String stateLabel;

    State(String stateLabel) { this.stateLabel = stateLabel; }
}
