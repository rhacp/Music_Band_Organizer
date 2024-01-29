package com.anghel.music_band_organizer.services.band;

import com.anghel.music_band_organizer.exceptions.band.BandAlreadyExistsException;
import com.anghel.music_band_organizer.exceptions.band.BandNotFoundException;
import com.anghel.music_band_organizer.models.dtos.band.BandDTO;
import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.repository.BandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BandServiceValidationImpl implements BandServiceValidation{

    private final BandRepository bandRepository;

    public BandServiceValidationImpl(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    @Override
    public void validateBandAlreadyExists(BandDTO bandDTO) {
        Band bandFound = bandRepository.findBandByBandName(bandDTO.getBandName());

        if (bandFound != null) {
            throw new BandAlreadyExistsException("A band with the name " + bandDTO.getBandName() + " already exists.");
        }
    }

    @Override
    public Band getValidBand(Long bandId, String methodName) {
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new BandNotFoundException("Band with the id " + bandId + " not found."));
        log.info("Band with the id {} retrieved. Method: {}", bandId, methodName);

        return band;
    }
}
