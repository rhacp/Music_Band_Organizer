package com.anghel.music_band_organizer.services.message;

import com.anghel.music_band_organizer.models.dtos.MessageDTO;
import com.anghel.music_band_organizer.models.entities.Message;
import com.anghel.music_band_organizer.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;
    private final MessageServiceValidation messageServiceValidation;
    private final ModelMapper modelMapper;

    public MessageServiceImpl(MessageRepository messageRepository, MessageServiceValidation messageServiceValidation, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.messageServiceValidation = messageServiceValidation;
        this.modelMapper = modelMapper;
    }

    @Override
    public MessageDTO createMessage() {
        return null;
    }

    @Transactional
    @Override
    public List<MessageDTO> getAllMessages() {
        List<Message> messageList = messageRepository.findAll();
        log.info("Message list retrieved. Method {}.", "getAllRehearsals");

        return messageList.stream()
                .map(message -> modelMapper.map(message, MessageDTO.class))
                .toList();
    }

    @Transactional
    @Override
    public MessageDTO getMessageById(Long messageId) {
        Message message = messageServiceValidation.getValidMessage(messageId, "getMessageById");

        return modelMapper.map(message, MessageDTO.class);
    }

    @Transactional
    @Override
    public String deleteMessageById(Long messageId) {
        messageServiceValidation.getValidMessage(messageId, "deleteMessageById");

        messageRepository.deleteById(messageId);
        log.info("Message with id {} deleted. Method: {}.", messageId, "deleteMessageById");

        return "Message with id " + messageId + " deleted.";
    }
}
