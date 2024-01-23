package com.anghel.music_band_organizer.controllers;

import com.anghel.music_band_organizer.models.dtos.BandDTO;
import com.anghel.music_band_organizer.services.band.BandService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bands")
public class BandController {

    private final BandService bandService;

    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<BandDTO> createBand(@Valid @RequestBody BandDTO bandDTO, @PathVariable Long userId) {
        return ResponseEntity.ok(bandService.createBand(bandDTO, userId));
    }

    @GetMapping
    public ResponseEntity<List<BandDTO>> getBands() {
        return ResponseEntity.ok(bandService.getAllBands());
    }

    @GetMapping("/{bandId}")
    public ResponseEntity<BandDTO> getBandById(@PathVariable Long bandId) {
        return ResponseEntity.ok(bandService.getBandById(bandId));
    }

    @DeleteMapping("/{bandId}")
    public ResponseEntity<String> deleteBandById(@PathVariable Long bandId) {
        return ResponseEntity.ok(bandService.deleteBandById(bandId));
    }

    @PostMapping("/{bandId}/openAI")
    public ResponseEntity<String> generateBandDescription(@PathVariable Long bandId) {
        return ResponseEntity.ok(bandService.generateBandDescription(bandId));
    }

    @PutMapping("/{bandId}/users/{userId}/{userToAddId}")
    public ResponseEntity<BandDTO> addUserToBand(@PathVariable Long bandId, @PathVariable Long userId, @PathVariable Long userToAddId) {
        return ResponseEntity.ok(bandService.addUserToBand(bandId, userId, userToAddId));
    }

    @PutMapping("/{bandId}/users/{userId}/admin/{userToChangeRoleId}")
    public ResponseEntity<BandDTO> makeUserAdminInBand(@PathVariable Long bandId, @PathVariable Long userId, @PathVariable Long userToChangeRoleId) {
        return ResponseEntity.ok(bandService.makeUserAdminInBand(bandId, userId, userToChangeRoleId));
    }
}
