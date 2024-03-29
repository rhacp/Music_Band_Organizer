package com.anghel.music_band_organizer.repository.user;

import com.anghel.music_band_organizer.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

    User findUserByEmail(String email);
}
