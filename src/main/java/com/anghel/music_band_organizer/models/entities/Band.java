package com.anghel.music_band_organizer.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "band_name", unique = true)
    private String bandName;

    @Column(name = "band_description")
    private String bandDescription;

    @OneToMany(mappedBy = "rehearsalBand")
    @JsonManagedReference(value = "rehearsal")
    private List<Rehearsal> rehearsalList = new ArrayList<>();

    @OneToMany(mappedBy = "postBand")
    @JsonManagedReference(value = "post")
    private List<Post> postList = new ArrayList<>();

    @ManyToMany(mappedBy = "bandList")
//    @JsonBackReference(value = "band")
    private List<User> userList = new ArrayList<>();
}
