package com.anghel.music_band_organizer.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "description")
    private String description;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "stage_name")
    private String stageName;

    @ElementCollection
    @CollectionTable(name = "user_past_experience",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @MapKeyColumn(name = "user_instrument")
    @Column(name = "past_experience")
    private Map<String, String> pastExperience = new LinkedHashMap<>();

    @ElementCollection
    @CollectionTable(name = "user_band_role",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @MapKeyColumn(name = "user_role")
    @Column(name = "band_name")
    private Map<String, String> bandRole = new LinkedHashMap<>();

    @ManyToMany(mappedBy = "userList")
    @JsonBackReference(value = "band")
    private List<Band> bandList = new ArrayList<>();

    @OneToMany(mappedBy = "toUser")
    @JsonManagedReference(value = "toUser")
    private List<Message> toUserList = new ArrayList<>();

    @OneToMany(mappedBy = "fromUser")
    @JsonManagedReference(value = "fromUser")
    private List<Message> fromUserList = new ArrayList<>();
}