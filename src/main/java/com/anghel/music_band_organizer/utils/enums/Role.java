package com.anghel.music_band_organizer.utils.enums;

import lombok.Getter;

@Getter
public enum Role {

    ROLE1("one"),
    ROLE2("two"),
    ROLE3("three");

    private final String roleLabel;

    Role(String roleLabel) { this.roleLabel = roleLabel; }
}
