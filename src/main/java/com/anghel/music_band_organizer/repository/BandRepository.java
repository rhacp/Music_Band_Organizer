package com.anghel.music_band_organizer.repository;

import com.anghel.music_band_organizer.models.entities.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {

    Band findBandById(Long id);

    Band findBandByBandName(String bandName);
}
