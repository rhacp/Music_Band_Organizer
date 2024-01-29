package com.anghel.music_band_organizer.controllers;

import com.anghel.music_band_organizer.models.dtos.message.GetAllMessagesDTO;
import com.anghel.music_band_organizer.models.dtos.message.MessageDTO;
import com.anghel.music_band_organizer.services.message.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/users/{fromUserId}/{toUserId}")
    public ResponseEntity<MessageDTO> sendMessage(@Valid @RequestBody MessageDTO messageDTO, @PathVariable Long fromUserId, @PathVariable Long toUserId) {
        return ResponseEntity.ok(messageService.sendMessage(messageDTO, fromUserId, toUserId));
    }

    @GetMapping
    public ResponseEntity<List<GetAllMessagesDTO>> getMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<MessageDTO> getMessageById(@PathVariable Long messageId) {
        return ResponseEntity.ok(messageService.getMessageById(messageId));
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<String> deleteMessageById(@PathVariable Long messageId) {
        return ResponseEntity.ok(messageService.deleteMessageById(messageId));
    }

    @GetMapping("/users/{fromUser}/{toUser}")
    public ResponseEntity<List<GetAllMessagesDTO>> getConversationBetweenUsers(@PathVariable Long fromUser, @PathVariable Long toUser) {
        return ResponseEntity.ok(messageService.getConversationBetweenUsers(fromUser, toUser));
    }
}
