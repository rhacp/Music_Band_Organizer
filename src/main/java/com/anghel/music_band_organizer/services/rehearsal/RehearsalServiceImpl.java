package com.anghel.music_band_organizer.services.rehearsal;

import com.anghel.music_band_organizer.models.dtos.RehearsalDTO;
import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.models.entities.Rehearsal;
import com.anghel.music_band_organizer.repository.rehearsal.RehearsalRepository;
import com.anghel.music_band_organizer.services.band.BandServiceValidation;
import com.anghel.music_band_organizer.utils.enums.State;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RehearsalServiceImpl implements RehearsalService{

    private final RehearsalRepository rehearsalRepository;
    private final RehearsalServiceValidation rehearsalServiceValidation;
    private final ModelMapper modelMapper;
    private final BandServiceValidation bandServiceValidation;

    public RehearsalServiceImpl(RehearsalRepository rehearsalRepository, RehearsalServiceValidation rehearsalServiceValidation, ModelMapper modelMapper, BandServiceValidation bandServiceValidation) {
        this.rehearsalRepository = rehearsalRepository;
        this.rehearsalServiceValidation = rehearsalServiceValidation;
        this.modelMapper = modelMapper;
        this.bandServiceValidation = bandServiceValidation;
    }

    @Transactional
    @Override
    public RehearsalDTO createRehearsal(RehearsalDTO rehearsalDTO, Long bandId) {
        Band band = bandServiceValidation.getValidBand(bandId, "createRehearsal");
        rehearsalServiceValidation.validateRehearsalAlreadyExists(rehearsalDTO);

        Rehearsal rehearsal = modelMapper.map(rehearsalDTO, Rehearsal.class);
        rehearsal.setRehearsalBand(band);
        rehearsal.setRehearsalState(State.DUE);
        Rehearsal savedRehearsal = rehearsalRepository.save(rehearsal);
        log.info("Rehearsal with id {} inserted in db. Method: {}", savedRehearsal.getId(), "createRehearsal");

        return modelMapper.map(savedRehearsal, RehearsalDTO.class);

    }
}
