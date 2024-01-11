package com.anghel.music_band_organizer.models.entities;

import jakarta.persistence.*;
import lombok.Data;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "band_id")
    private Band postBand;
}
