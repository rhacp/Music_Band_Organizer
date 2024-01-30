package com.anghel.music_band_organizer.repository.rehearsal;

import com.anghel.music_band_organizer.models.entities.Rehearsal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CustomRehearsalRepository {

    List<Rehearsal> findFilteredRehearsal(Long rehearsalId, LocalDate rehearsalDate, LocalTime rehearsalTime);
}
