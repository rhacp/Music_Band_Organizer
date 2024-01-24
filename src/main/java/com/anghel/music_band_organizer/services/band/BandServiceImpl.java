package com.anghel.music_band_organizer.services.band;

import com.anghel.music_band_organizer.models.dtos.BandDTO;
import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.services.open_ai.OpenAIImpl;
import com.anghel.music_band_organizer.models.entities.User;
import com.anghel.music_band_organizer.repository.BandRepository;
import com.anghel.music_band_organizer.services.user.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BandServiceImpl implements BandService{

    private final BandRepository bandRepository;
    private final BandServiceValidation bandServiceValidation;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public BandServiceImpl(BandRepository bandRepository, BandServiceValidation bandServiceValidation, ModelMapper modelMapper, UserService userService, OpenAIImpl openAI) {
        this.bandRepository = bandRepository;
        this.bandServiceValidation = bandServiceValidation;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.openAI = openAI;
    }

    @Transactional
    @Override
    public BandDTO createBand(BandDTO bandDTO, Long userId) {
        bandServiceValidation.validateBandAlreadyExists(bandDTO);
        User user = userService.createBand(userId, bandDTO.getBandName());

        Band band = modelMapper.map(bandDTO, Band.class);
        band.getUserList().add(user);
        Band savedBand = bandRepository.save(band);
        log.info("Band with id {} inserted in db. Method: {}", savedBand.getId(), "createBand");

        return modelMapper.map(savedBand, BandDTO.class);
    }

    @Transactional
    @Override
    public List<BandDTO> getAllBands() {
        List<Band> bandList = bandRepository.findAll();
        log.info("Band list retrieved. Method: {}.", "getAllBands");

        return bandList.stream()
                .map(band -> modelMapper.map(band, BandDTO.class))
                .toList();
    }

    @Transactional
    @Override
    public BandDTO getBandById(Long bandId) {
        Band band = bandServiceValidation.getValidBand(bandId, "getBandById");

        return modelMapper.map(band, BandDTO.class);
    }

    @Transactional
    @Override
    public String deleteBandById(Long bandId) {
        bandServiceValidation.getValidBand(bandId, "deleteBandById");

        bandRepository.deleteById(bandId);
        log.info("Band with id {} deleted. Method: {}", bandId, "deleteBandById");

        return "Band with id " + bandId + " deleted.";
    }

    @Override
    public BandDTO addUserToBand(Long bandId, Long userId, Long userToAddId) {
        Band band = bandServiceValidation.getValidBand(bandId, "addUserToBand");
        User user = userService.addUserToBand(userId, userToAddId, band, "addUserToBand");

        band.getUserList().add(user);
        Band savedBand = bandRepository.save(band);
        log.info("Band with id {} had user with id {} added. Method: {}", savedBand.getId(), user.getId(), "addUserToBand");

        return modelMapper.map(savedBand, BandDTO.class);
    }

    @Override
    public BandDTO makeUserAdminInBand(Long bandId, Long userId, Long userToChangeRoleId) {
        Band band = bandServiceValidation.getValidBand(bandId, "addUserToBand");
        User user = userService.makeUserAdminInBand(userId, userToChangeRoleId, band, "makeUserAdminInBand");

        band.getUserList().add(user);
        Band savedBand = bandRepository.save(band);
        log.info("Band with id {} had user with id {} added. Method: {}", savedBand.getId(), user.getId(), "createBand");

        return modelMapper.map(savedBand, BandDTO.class);
    }

    @Override
    public Band getValidBandForCreateRehearsal(Long bandId, String methodName) {
        return bandServiceValidation.getValidBand(bandId, methodName);
    }

    @Override
    public Band getValidBandForDeletePost(Long bandId, String methodName) {
        return bandServiceValidation.getValidBand(bandId, methodName);
    }
}
