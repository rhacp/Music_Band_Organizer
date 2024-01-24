package com.anghel.music_band_organizer.services.post;

import com.anghel.music_band_organizer.models.dtos.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO, Long userId, Long bandId);

    List<PostDTO> getAllPosts();

    PostDTO getPostById(Long postId);

    String deletePostById(Long postId, Long userId, Long bandId);
}
