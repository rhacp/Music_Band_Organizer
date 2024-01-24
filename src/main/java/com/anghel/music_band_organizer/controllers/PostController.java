package com.anghel.music_band_organizer.controllers;

import com.anghel.music_band_organizer.models.dtos.PostDTO;
import com.anghel.music_band_organizer.services.post.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/users/{userId}/bands/{bandId}")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO, @PathVariable Long userId, @PathVariable Long bandId) {
        return ResponseEntity.ok(postService.createPost(postDTO, userId, bandId));
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @DeleteMapping("{postId}/users/{userId}/bands/{bandId}")
    public ResponseEntity<String> deletePostById(@PathVariable Long postId, @PathVariable Long userId, @PathVariable Long bandId) {
        return ResponseEntity.ok(postService.deletePostById(postId, userId, bandId));
    }
}
