package com.anghel.music_band_organizer.utils.enums;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("admin"),
    MEMBER("member");

    private final String roleLabel;

    Role(String roleLabel) { this.roleLabel = roleLabel; }
}
