package com.anghel.music_band_organizer.services.message;

import com.anghel.music_band_organizer.models.dtos.MessageDTO;
import com.anghel.music_band_organizer.models.entities.Message;
import com.anghel.music_band_organizer.models.entities.User;
import com.anghel.music_band_organizer.repository.MessageRepository;
import com.anghel.music_band_organizer.services.user.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;
    private final MessageServiceValidation messageServiceValidation;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final DateTimeFormatter formatter;

    public MessageServiceImpl(MessageRepository messageRepository, MessageServiceValidation messageServiceValidation, ModelMapper modelMapper, UserService userService) {
        this.messageRepository = messageRepository;
        this.messageServiceValidation = messageServiceValidation;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.formatter = DateTimeFormatter.ofPattern("HH:mm");
    }

    @Override
    public MessageDTO sendMessage(MessageDTO messageDTO, Long fromUserId, Long toUserId) {
        User fromUser = userService.sendMessage(fromUserId, "fromUserId");
        User toUser = userService.sendMessage(toUserId, "toUserId");

        Message message = modelMapper.map(messageDTO, Message.class);
        message.setFromUser(fromUser);
        message.setToUser(toUser);
        message.setMessageDate(LocalDate.now());
        message.setMessageTime(LocalTime.parse(LocalTime.now().format(formatter),formatter));
        Message savedMessage = messageRepository.save(message);
        log.info("Message with id {} inserted in db. Method: {}", savedMessage.getId(), "sendMessage");

        return modelMapper.map(savedMessage, MessageDTO.class);
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
