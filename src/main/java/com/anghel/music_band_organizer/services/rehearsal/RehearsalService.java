package com.anghel.music_band_organizer.services.rehearsal;

import com.anghel.music_band_organizer.models.dtos.rehearsal.GetAllRehearsalsDTO;
import com.anghel.music_band_organizer.models.dtos.rehearsal.RehearsalDTO;

import java.util.List;

public interface RehearsalService {

    RehearsalDTO createRehearsal(RehearsalDTO rehearsalDTO, Long userId, Long bandId);

    List<GetAllRehearsalsDTO> getAllRehearsals();

    RehearsalDTO getRehearsalById(Long rehearsalId);

    String deleteRehearsalById(Long rehearsalId);

    RehearsalDTO finishRehearsal(Long rehearsalId, Long userId);
}
