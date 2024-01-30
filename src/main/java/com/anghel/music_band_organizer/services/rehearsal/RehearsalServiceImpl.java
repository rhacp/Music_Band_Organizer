package com.anghel.music_band_organizer.services.rehearsal;

import com.anghel.music_band_organizer.models.dtos.rehearsal.GetAllRehearsalsDTO;
import com.anghel.music_band_organizer.models.dtos.rehearsal.RehearsalDTO;
import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.models.entities.Mail;
import com.anghel.music_band_organizer.models.entities.Rehearsal;
import com.anghel.music_band_organizer.repository.rehearsal.RehearsalRepository;
import com.anghel.music_band_organizer.services.band.BandService;
import com.anghel.music_band_organizer.services.mail.MailService;
import com.anghel.music_band_organizer.services.user.UserService;
import com.anghel.music_band_organizer.utils.enums.Availability;
import com.anghel.music_band_organizer.utils.enums.State;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
public class RehearsalServiceImpl implements RehearsalService{

    private final RehearsalRepository rehearsalRepository;
    private final RehearsalServiceValidation rehearsalServiceValidation;
    private final ModelMapper modelMapper;
    private final BandService bandService;
    private final UserService userService;
    private final MailService mailService;

    public RehearsalServiceImpl(RehearsalRepository rehearsalRepository, RehearsalServiceValidation rehearsalServiceValidation, ModelMapper modelMapper, BandService bandService, UserService userService, MailService mailService) {
        this.rehearsalRepository = rehearsalRepository;
        this.rehearsalServiceValidation = rehearsalServiceValidation;
        this.modelMapper = modelMapper;
        this.bandService = bandService;
        this.userService = userService;
        this.mailService = mailService;
    }

    @Transactional
    @Override
    public RehearsalDTO createRehearsal(RehearsalDTO rehearsalDTO, Long userId, Long bandId) {
        Band band = bandService.getValidBandForCreateRehearsal(bandId, "createRehearsal");
        userService.checkUserForCreateRehearsal(userId, "createRehearsal", band);
        rehearsalServiceValidation.validateRehearsalAlreadyExists(rehearsalDTO, band);

        Rehearsal rehearsal = modelMapper.map(rehearsalDTO, Rehearsal.class);
        rehearsal.setRehearsalBand(band);
        rehearsal.setRehearsalState(State.DUE);
        rehearsal.setRehearsalAvailability(convertAvailability(rehearsalDTO.getRehearsalAvailability()));
        rehearsal.setRehearsalDate(LocalDate.parse(rehearsalDTO.getRehearsalDate()));
        rehearsal.setRehearsalTime(LocalTime.parse(rehearsalDTO.getRehearsalTime()));

        Rehearsal savedRehearsal = rehearsalRepository.save(rehearsal);
        log.info("Rehearsal with id {} inserted in db. Method: {}.", savedRehearsal.getId(), "createRehearsal");

        Mail mail = mailService.prepareMailRehearsal(savedRehearsal, "createRehearsal");
        band.getUserList()
                .forEach(element -> mailService.sendMail(element.getEmail(), mail, "createRehearsal"));

        return modelMapper.map(savedRehearsal, RehearsalDTO.class);
    }

    @Transactional
    @Override
    public List<GetAllRehearsalsDTO> getAllRehearsals() {
        List<Rehearsal> rehearsalList = rehearsalRepository.findAll();
        log.info("Rehearsal list retrieved. Method {}.", "getAllRehearsals");

        return rehearsalList.stream()
                .map(rehearsal -> modelMapper.map(rehearsal, GetAllRehearsalsDTO.class))
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

    @Override
    public RehearsalDTO finishRehearsal(Long rehearsalId, Long userId) {
        Rehearsal rehearsal = rehearsalServiceValidation.getValidRehearsal(rehearsalId, "finishRehearsal");
        userService.checkUserForFinishRehearsal(userId, rehearsal, "finishRehearsal");

        rehearsal.setRehearsalState(State.DONE);
        Rehearsal savedRehearsal = rehearsalRepository.save(rehearsal);
        log.info("Rehearsal with id {} had the state changed to DONE. Method: {}.", savedRehearsal.getId(), "finishRehearsal");

        return modelMapper.map(savedRehearsal, RehearsalDTO.class);
    }

    private Availability convertAvailability(String availabilityLabel) {
        switch (availabilityLabel.toLowerCase()) {
            case "public" -> { return Availability.PUBLIC; }
            case "private" -> { return Availability.PRIVATE; }
            default -> { return null; }
        }
    }
}
