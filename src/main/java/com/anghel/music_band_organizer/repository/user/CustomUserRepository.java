package com.anghel.music_band_organizer.repository.user;

import com.anghel.music_band_organizer.models.entities.User;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomUserRepository {

    List<User> findFilteredUser(Long userId, String firstName, String lastName, String userEmail, LocalDateTime userBirthday, String pastExperience);
}
