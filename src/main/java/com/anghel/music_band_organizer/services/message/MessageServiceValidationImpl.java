package com.anghel.music_band_organizer.services.message;

import com.anghel.music_band_organizer.exceptions.message.MessageAlreadyExistsException;
import com.anghel.music_band_organizer.exceptions.message.MessageNotFoundException;
import com.anghel.music_band_organizer.models.dtos.message.MessageDTO;
import com.anghel.music_band_organizer.models.entities.Message;
import com.anghel.music_band_organizer.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageServiceValidationImpl implements MessageServiceValidation{

    private final MessageRepository messageRepository;

    public MessageServiceValidationImpl(MessageRepository messageRepository) { this.messageRepository = messageRepository; }

    @Override
    public void validateMessageAlreadyExists(MessageDTO messageDTO) {
        Message message = messageRepository.findMessageById(messageDTO.getId());

        if (message != null) {
            throw new MessageAlreadyExistsException("A message with the date " + messageDTO.getMessageDate() + " and time " + messageDTO.getMessageTime() + " already exists.");
        }
    }

    @Override
    public Message getValidMessage(Long messageId, String methodName) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new MessageNotFoundException("Message with id " + messageId + " not found."));
        log.info("Message with id {} retrieved. Method: {}.", messageId, methodName);

        return message;
    }
}
