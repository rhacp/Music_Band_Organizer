package com.anghel.music_band_organizer.repository.rehearsal;

import com.anghel.music_band_organizer.models.entities.Rehearsal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface RehearsalRepository extends JpaRepository<Rehearsal, Long>, CustomRehearsalRepository {

    Rehearsal findRehearsalById(Long rehearsalId);

    Rehearsal findRehearsalByRehearsalDate(LocalDate rehearsalDate);

    Rehearsal findRehearsalByRehearsalTime(LocalTime rehearsalTime);

    Rehearsal findByRehearsalDateAndRehearsalTime(LocalDate rehearsalDate, LocalTime rehearsalTime);
}
