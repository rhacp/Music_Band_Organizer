package com.anghel.music_band_organizer.repository.user;

import com.anghel.music_band_organizer.models.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface CustomUserRepository {

    List<User> findFilteredUser(Long userId, String firstName, String lastName, String email, LocalDate birthday, String pastExperience, String stageName);
}
