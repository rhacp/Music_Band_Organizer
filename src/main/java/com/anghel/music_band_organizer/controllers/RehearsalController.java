package com.anghel.music_band_organizer.controllers;

import com.anghel.music_band_organizer.models.dtos.RehearsalDTO;
import com.anghel.music_band_organizer.services.rehearsal.RehearsalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rehearsals")
public class RehearsalController {

    private final RehearsalService rehearsalService;

    public RehearsalController(RehearsalService rehearsalService) {
        this.rehearsalService = rehearsalService;
    }

    @PostMapping("/{bandId}")
    public ResponseEntity<RehearsalDTO> createRehearsal(@Valid @RequestBody RehearsalDTO rehearsalDTO, @PathVariable Long bandId) {
        return ResponseEntity.ok(rehearsalService.createRehearsal(rehearsalDTO, bandId));
    }

    @GetMapping
    public ResponseEntity<List<RehearsalDTO>> getRehearsals() {
        return ResponseEntity.ok(rehearsalService.getAllRehearsals());
    }

    @GetMapping("/{rehearsalId}")
    public ResponseEntity<RehearsalDTO> getRehearsalById(@PathVariable Long rehearsalId) {
        return ResponseEntity.ok(rehearsalService.getRehearsalById(rehearsalId));
    }

    @DeleteMapping("/{rehearsalId}")
    public ResponseEntity<String> deleteRehearsalById(@PathVariable Long rehearsalId) {
        return ResponseEntity.ok(rehearsalService.deleteRehearsalById(rehearsalId));
    }
}
