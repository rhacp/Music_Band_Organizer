package com.anghel.music_band_organizer.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_description")
    private String postDescription;

    @Column(name = "post_genre")
    private String postGenre;

    @Column(name = "post_recording_title")
    private String postRecordingTitle;

    @Column(name = "user_openai")
    private Boolean useOpenAIForDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_band_id")
    @JsonBackReference(value = "post")
    private Band postBand;
}
