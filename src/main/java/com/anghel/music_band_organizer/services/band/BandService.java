package com.anghel.music_band_organizer.services.band;

import com.anghel.music_band_organizer.models.dtos.BandDTO;
import com.anghel.music_band_organizer.models.entities.Band;

import java.util.List;

public interface BandService {

    BandDTO createBand(BandDTO bandDTO, Long userId);

    List<BandDTO> getAllBands();

    BandDTO getBandById(Long bandId);

    String deleteBandById(Long bandId);

//    String generateBandDescription(Long bandId);

    BandDTO addUserToBand(Long bandId, Long userId, Long userToAddId);

    BandDTO makeUserAdminInBand(Long bandId, Long userId, Long userToChangeRoleId);

    Band getValidBandForCreateRehearsal(Long bandId, String methodName);

    Band getValidBandForDeletePost(Long bandId, String methodName);
}
