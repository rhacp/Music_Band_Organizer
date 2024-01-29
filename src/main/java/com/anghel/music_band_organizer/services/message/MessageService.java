package com.anghel.music_band_organizer.services.message;

import com.anghel.music_band_organizer.models.dtos.message.GetAllMessagesDTO;
import com.anghel.music_band_organizer.models.dtos.message.MessageDTO;

import java.util.List;

public interface MessageService {

    MessageDTO sendMessage(MessageDTO messageDTO, Long fromUserId, Long toUserId);

    List<GetAllMessagesDTO> getAllMessages();

    MessageDTO getMessageById(Long messageId);

    String deleteMessageById(Long rehearsalId);

    List<GetAllMessagesDTO> getConversationBetweenUsers(Long fromUser, Long toUser);
}
