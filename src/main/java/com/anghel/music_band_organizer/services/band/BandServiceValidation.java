package com.anghel.music_band_organizer.services.band;

import com.anghel.music_band_organizer.models.dtos.BandDTO;
import com.anghel.music_band_organizer.models.entities.Band;

public interface BandServiceValidation {

    void validateBandAlreadyExists(BandDTO bandDTO);

    Band getValidBand(Long bandId, String methodName);
}
