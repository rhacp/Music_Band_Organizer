package com.anghel.music_band_organizer.services.message;

import com.anghel.music_band_organizer.models.dtos.MessageDTO;

import java.util.List;

public interface MessageService {

    MessageDTO sendMessage(MessageDTO messageDTO, Long toUserId, Long fromUserId);

    List<MessageDTO> getAllMessages();

    MessageDTO getMessageById(Long messageId);

    String deleteMessageById(Long rehearsalId);
}
