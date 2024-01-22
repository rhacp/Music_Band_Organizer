package com.anghel.music_band_organizer.services.rehearsal;

import com.anghel.music_band_organizer.models.dtos.rehearsal.RehearsalDTO;
import com.anghel.music_band_organizer.models.entities.Rehearsal;

public interface RehearsalServiceValidation {

    void validateRehearsalAlreadyExists(RehearsalDTO rehearsalDTO);

    Rehearsal getValidRehearsal(Long rehearsalId, String methodName);
}
