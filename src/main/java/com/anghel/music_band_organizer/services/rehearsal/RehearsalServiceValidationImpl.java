package com.anghel.music_band_organizer.services.rehearsal;

import com.anghel.music_band_organizer.exceptions.rehearsal.RehearsalAlreadyExistsException;
import com.anghel.music_band_organizer.exceptions.rehearsal.RehearsalNotFoundException;
import com.anghel.music_band_organizer.models.dtos.RehearsalDTO;
import com.anghel.music_band_organizer.models.entities.Rehearsal;
import com.anghel.music_band_organizer.repository.rehearsal.RehearsalRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RehearsalServiceValidationImpl implements RehearsalServiceValidation{

    private final RehearsalRepository rehearsalRepository;

    public RehearsalServiceValidationImpl(RehearsalRepository rehearsalRepository) { this.rehearsalRepository = rehearsalRepository; }

    @Transactional
    @Override
    public void validateRehearsalAlreadyExists(RehearsalDTO rehearsalDTO) {
        // dynamic query to get rehearsal by date and time;
        Rehearsal rehearsal = rehearsalRepository.findRehearsalByRehearsalDate(rehearsalDTO.getRehearsalDate());

        if (rehearsal != null) {
            throw new RehearsalAlreadyExistsException("A rehearsal with the date + " + rehearsal.getRehearsalDate() + " and time " + rehearsal.getRehearsalTime() + " already exists.");
        }
    }

    @Transactional
    @Override
    public Rehearsal getValidRehearsal(Long rehearsalId, String methodName) {
        Rehearsal rehearsal = rehearsalRepository.findById(rehearsalId)
                .orElseThrow(() -> new RehearsalNotFoundException("Rehearsal with id " + rehearsalId + "not found."));
        log.info("Rehearsal with id {} retrieved. Method: {}.", rehearsalId, methodName);

        return rehearsal;
    }
}
