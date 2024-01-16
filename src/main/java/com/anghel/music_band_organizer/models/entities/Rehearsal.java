package com.anghel.music_band_organizer.models.entities;

import com.anghel.music_band_organizer.utils.enums.State;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "rehearsals")
public class Rehearsal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rehearsal_state")
    private State rehearsalState;

    @Column(name = "rehearsal_time")
    private LocalDateTime rehearsalTime;

    @Column(name = "rehearsal_duration_hours")
    private Integer rehearsalDurationHours;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rehersal_band_id")
    @JsonBackReference(value = "rehearsal")
    private Band rehearsalBand;
}
