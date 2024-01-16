package com.anghel.music_band_organizer.repository;

import com.anghel.music_band_organizer.models.entities.Rehearsal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RehearsalRepository extends JpaRepository<Rehearsal, Long> {

}
