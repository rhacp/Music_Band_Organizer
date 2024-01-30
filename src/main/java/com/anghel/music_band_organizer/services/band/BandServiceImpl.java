package com.anghel.music_band_organizer.services.band;

import com.anghel.music_band_organizer.models.dtos.band.BandDTO;
import com.anghel.music_band_organizer.models.dtos.band.GetAllBandsDTO;
import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.models.entities.Mail;
import com.anghel.music_band_organizer.models.entities.User;
import com.anghel.music_band_organizer.repository.BandRepository;
import com.anghel.music_band_organizer.services.mail.MailService;
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
    private final MailService mailService;

    public BandServiceImpl(BandRepository bandRepository, BandServiceValidation bandServiceValidation, ModelMapper modelMapper, UserService userService, MailService mailService) {
        this.bandRepository = bandRepository;
        this.bandServiceValidation = bandServiceValidation;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.mailService = mailService;
    }

    @Transactional
    @Override
    public BandDTO createBand(BandDTO bandDTO, Long userId) {
        bandServiceValidation.validateBandAlreadyExists(bandDTO);
        User user = userService.makeUserOwnerForCreateBand(userId, bandDTO.getBandName(), "createBand");

        Band band = modelMapper.map(bandDTO, Band.class);
        band.getUserList().add(user);
        Band savedBand = bandRepository.save(band);
        log.info("Band with id {} inserted in db. Method: {}", savedBand.getId(), "createBand");

        return modelMapper.map(savedBand, BandDTO.class);
    }

    @Transactional
    @Override
    public List<GetAllBandsDTO> getAllBands() {
        List<Band> bandList = bandRepository.findAll();
        log.info("Band list retrieved. Method: {}.", "getAllBands");

        return bandList.stream()
                .map(band -> modelMapper.map(band, GetAllBandsDTO.class))
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
    public BandDTO updateBandById(Long bandId, Long userId, BandDTO bandDTO) {
        Band band = bandServiceValidation.getValidBand(bandId, "updateBandById");
        userService.checkUserAdminInBandForUpdateBand(userId, band, "updateBandById");

        band.setBandDescription(bandDTO.getBandDescription());
        Band savedBand = bandRepository.save(band);
        log.info("Band with id {} updated in db. Method: {}", savedBand.getId(), "updateBandById");

        return modelMapper.map(savedBand, BandDTO.class);
    }

    @Override
    public BandDTO addUserToBand(Long bandId, Long userId, Long userToAddId) {
        Band band = bandServiceValidation.getValidBand(bandId, "addUserToBand");
        User user = userService.addUserToBand(userId, userToAddId, band, "addUserToBand");

        band.getUserList().add(user);
        Band savedBand = bandRepository.save(band);
        log.info("Band with id {} had user with id {} added. Method: {}", savedBand.getId(), user.getId(), "addUserToBand");

        Mail mail = mailService.prepareMailBand(savedBand, "addUserToBand");
        mailService.sendMail(user.getEmail(), mail, "addUserToBand");

        return modelMapper.map(savedBand, BandDTO.class);
    }

    @Override
    public BandDTO makeUserAdminInBand(Long bandId, Long userId, Long userToChangeRoleId) {
        Band band = bandServiceValidation.getValidBand(bandId, "makeUserAdminInBand");
        User user = userService.makeUserAdminInBand(userId, userToChangeRoleId, band, "makeUserAdminInBand");

        log.info("Band with id {} had user with id {} added. Method: {}", band.getId(), user.getId(), "makeUserAdminInBand");

        return modelMapper.map(band, BandDTO.class);
    }

    @Override
    public BandDTO changeUserToMemberInBand(Long bandId, Long userId, Long userToChangeRoleId) {
        Band band = bandServiceValidation.getValidBand(bandId, "changeUserToMemberInBand");
        User user = userService.changeUserToMemberInBand(userId, userToChangeRoleId, band, "changeUserToMemberInBand");

        log.info("User with id {} was made admin in band {}. Method: {}", user.getId(), band.getBandName(), "changeUserToMemberInBand");

        return modelMapper.map(band, BandDTO.class);
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
