package com.anghel.music_band_organizer.services.band;

import com.anghel.music_band_organizer.models.dtos.BandDTO;

import java.util.List;

public interface BandService {

    BandDTO createBand(BandDTO bandDTO, Long userId);

    List<BandDTO> getAllBands();

    BandDTO getBandById(Long bandId);

    String deleteBandById(Long bandId);
}
