package com.anghel.music_band_organizer.controllers;

import com.anghel.music_band_organizer.models.dtos.user.GetAllUsersDTO;
import com.anghel.music_band_organizer.models.dtos.user.UserDTO;
import com.anghel.music_band_organizer.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @GetMapping
    public ResponseEntity<List<GetAllUsersDTO>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<UserDTO>> getFilteredUsers(@RequestParam(required = false) Long userId,
                                                          @RequestParam(required = false) String firstName,
                                                          @RequestParam(required = false) String lastName,
                                                          @RequestParam(required = false) String email,
                                                          @RequestParam(required = false) LocalDate birthday,
                                                          @RequestParam(required = false) String pastExperience,
                                                          @RequestParam(required = false) String stageName) {
        return ResponseEntity.ok(userService.getFilteredUsers(userId, firstName, lastName, email, birthday, pastExperience, stageName));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable Long userId,@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUserById(userId, userDTO));
    }
}
