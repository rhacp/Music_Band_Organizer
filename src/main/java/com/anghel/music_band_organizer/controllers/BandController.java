package com.anghel.music_band_organizer.controllers;

import com.anghel.music_band_organizer.models.dtos.band.BandDTO;
import com.anghel.music_band_organizer.models.dtos.band.GetAllBandsDTO;
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

    @PostMapping("/users/{userId}")
    public ResponseEntity<BandDTO> createBand(@Valid @RequestBody BandDTO bandDTO, @PathVariable Long userId) {
        return ResponseEntity.ok(bandService.createBand(bandDTO, userId));
    }

    @GetMapping
    public ResponseEntity<List<GetAllBandsDTO>> getAllBands() {
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

    @PutMapping("/{bandId}/users/{userId}/{userToAddId}")
    public ResponseEntity<BandDTO> addUserToBand(@PathVariable Long bandId, @PathVariable Long userId, @PathVariable Long userToAddId) {
        return ResponseEntity.ok(bandService.addUserToBand(bandId, userId, userToAddId));
    }

    @PutMapping("/{bandId}/users/{userId}/admin/{userToChangeRoleId}")
    public ResponseEntity<BandDTO> makeUserAdminInBand(@PathVariable Long bandId, @PathVariable Long userId, @PathVariable Long userToChangeRoleId) {
        return ResponseEntity.ok(bandService.makeUserAdminInBand(bandId, userId, userToChangeRoleId));
    }

    @PutMapping("/{bandId}/users/{userId}")
    public ResponseEntity<BandDTO> updateBandById(@PathVariable Long bandId, @PathVariable Long userId, @RequestBody BandDTO bandDTO) {
        return ResponseEntity.ok(bandService.updateBandById(bandId, userId, bandDTO));
    }

    @PutMapping("/{bandId}/users/{userId}/member/{userToChangeRoleId}")
    public ResponseEntity<BandDTO> changeUserToMemberInBand(@PathVariable Long bandId, @PathVariable Long userId, @PathVariable Long userToChangeRoleId) {
        return ResponseEntity.ok(bandService.changeUserToMemberInBand(bandId, userId, userToChangeRoleId));
    }
}
