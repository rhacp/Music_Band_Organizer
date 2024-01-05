package com.anghel.music_band_organizer.repository;

import com.anghel.music_band_organizer.models.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User findById(Long id);
}
