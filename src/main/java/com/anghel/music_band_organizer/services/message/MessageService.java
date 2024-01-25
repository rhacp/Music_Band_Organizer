package com.anghel.music_band_organizer.services.message;

import com.anghel.music_band_organizer.models.dtos.MessageDTO;

import java.util.List;

public interface MessageService {

    MessageDTO sendMessage(MessageDTO messageDTO, Long fromUserId, Long toUserId);

    List<MessageDTO> getAllMessages();

    MessageDTO getMessageById(Long messageId);

    String deleteMessageById(Long rehearsalId);

    List<MessageDTO> getConversationBetweenUsers(Long fromUser, Long toUser);
}
