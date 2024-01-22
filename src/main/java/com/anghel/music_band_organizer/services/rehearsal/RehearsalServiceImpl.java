package com.anghel.music_band_organizer.services.rehearsal;

import com.anghel.music_band_organizer.models.dtos.RehearsalDTO;
import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.models.entities.Rehearsal;
import com.anghel.music_band_organizer.repository.rehearsal.RehearsalRepository;
import com.anghel.music_band_organizer.services.band.BandServiceValidation;
import com.anghel.music_band_organizer.utils.enums.Availability;
import com.anghel.music_band_organizer.utils.enums.State;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
        rehearsal.setRehearsalAvailability(convertAvailability(rehearsalDTO.getRehearsalAvailability()));

        Rehearsal savedRehearsal = rehearsalRepository.save(rehearsal);
        log.info("Rehearsal with id {} inserted in db. Method: {}", savedRehearsal.getId(), "createRehearsal");

        return modelMapper.map(savedRehearsal, RehearsalDTO.class);

    }

    @Transactional
    @Override
    public List<RehearsalDTO> getAllRehearsals() {
        List<Rehearsal> rehearsalList = rehearsalRepository.findAll();
        log.info("Rehearsal list retrieved. Method {}.", "getAllRehearsals");

        return rehearsalList.stream()
                .map(rehearsal -> modelMapper.map(rehearsal, RehearsalDTO.class))
                .toList();
    }

    @Transactional
    @Override
    public RehearsalDTO getRehearsalById(Long rehearsalId) {
        Rehearsal rehearsal = rehearsalServiceValidation.getValidRehearsal(rehearsalId, "getRehearsalById");

        return modelMapper.map(rehearsal, RehearsalDTO.class);
    }

    @Transactional
    @Override
    public String deleteRehearsalById(Long rehearsalId) {
        rehearsalServiceValidation.getValidRehearsal(rehearsalId, "deleteRehearsalById");

        rehearsalRepository.deleteById(rehearsalId);
        log.info("Rehearsal with id {} deleted. Method: {}.", rehearsalId, "deleteRehearsalById");

        return "Rehearsal with id " + rehearsalId + " deleted.";
    }

    private Availability convertAvailability(String availabilityLabel) {
        switch (availabilityLabel.toLowerCase()) {
            case "public" -> { return Availability.PUBLIC; }
            case "private" -> { return Availability.PRIVATE; }
            default -> { return null; }
        }
    }
}
