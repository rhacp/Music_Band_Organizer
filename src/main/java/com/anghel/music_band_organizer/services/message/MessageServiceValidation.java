package com.anghel.music_band_organizer.services.message;

import com.anghel.music_band_organizer.models.dtos.MessageDTO;
import com.anghel.music_band_organizer.models.entities.Message;

public interface MessageServiceValidation {

    void validateMessageAlreadyExists(MessageDTO messageDTO);

    Message getValidMessage(Long messageId, String methodName);
}
