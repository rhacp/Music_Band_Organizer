package com.anghel.music_band_organizer.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "email", unique = true)
    private String email;

    @ElementCollection
    @CollectionTable(name = "user_past_experience",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @MapKeyColumn(name = "user_instrument")
    @Column(name = "past_experience")
    private Map<String, String> pastExperience = new LinkedHashMap<>();

    @ManyToMany
    @JoinTable(
            name = "user_band_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "band_id")
    )
    private List<Band> bandList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "user_band_role",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @MapKeyColumn(name = "user_role")
    @Column(name = "band_name")
    private Map<String, String> bandRole = new LinkedHashMap<>();
}