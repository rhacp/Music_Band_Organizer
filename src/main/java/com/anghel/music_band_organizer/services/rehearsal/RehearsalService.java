package com.anghel.music_band_organizer.services.rehearsal;

import com.anghel.music_band_organizer.models.dtos.RehearsalDTO;

public interface RehearsalService {

    RehearsalDTO createRehearsal(RehearsalDTO rehearsalDTO, Long bandId);
}
