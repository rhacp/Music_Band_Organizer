package com.anghel.music_band_organizer.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "bands")
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "band_name")
    private String bandName;

    @Column(name = "band_description")
    private String bandDescription;

    @OneToMany(mappedBy = "band")
    private List<Rehearsal> rehearsalList = new ArrayList<>();

    @OneToMany(mappedBy = "band")
    private List<Post> postList = new ArrayList<>();

    @ManyToMany(mappedBy = "bandList")
    private List<User> userList = new ArrayList<>();
}
