package com.anghel.music_band_organizer.services.band;

import com.anghel.music_band_organizer.models.dtos.band.BandDTO;
import com.anghel.music_band_organizer.models.dtos.band.GetAllBandsDTO;
import com.anghel.music_band_organizer.models.entities.Band;

import java.util.List;

public interface BandService {

    BandDTO createBand(BandDTO bandDTO, Long userId);

    List<GetAllBandsDTO> getAllBands();

    BandDTO getBandById(Long bandId);

    String deleteBandById(Long bandId);

    BandDTO updateBandById(Long bandId, Long userId, BandDTO bandDTO);

    BandDTO addUserToBand(Long bandId, Long userId, Long userToAddId);

    BandDTO makeUserAdminInBand(Long bandId, Long userId, Long userToChangeRoleId);

    BandDTO changeUserToMemberInBand(Long bandId, Long  userId, Long userToChangeRoleId);

    Band getValidBandForCreateRehearsal(Long bandId, String methodName);

    Band getValidBandForDeletePost(Long bandId, String methodName);
}
