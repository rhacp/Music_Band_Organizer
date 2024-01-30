package com.anghel.music_band_organizer.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "postBand")
    @JsonManagedReference(value = "post")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "rehearsalBand")
    @JsonManagedReference(value = "rehearsal")
    private List<Rehearsal> rehearsalList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_band_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "band_id")
    )
    private List<User> userList = new ArrayList<>();
}
