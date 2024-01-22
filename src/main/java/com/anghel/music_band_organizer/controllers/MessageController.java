package com.anghel.music_band_organizer.controllers;

import com.anghel.music_band_organizer.models.dtos.MessageDTO;
import com.anghel.music_band_organizer.models.dtos.RehearsalDTO;
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

    @PostMapping
    public ResponseEntity<RehearsalDTO> sendMessage(@Valid @RequestBody RehearsalDTO rehearsalDTO) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<MessageDTO>> getMessages() {
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
}
