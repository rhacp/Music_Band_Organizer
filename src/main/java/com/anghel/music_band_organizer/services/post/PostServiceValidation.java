package com.anghel.music_band_organizer.services.post;

import com.anghel.music_band_organizer.models.entities.Post;

public interface PostServiceValidation {

    Post getValidPost(Long postId, String methodName);
}
