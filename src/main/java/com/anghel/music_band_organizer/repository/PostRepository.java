package com.anghel.music_band_organizer.repository;

import com.anghel.music_band_organizer.models.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostById(Long postId);
}
