package com.anghel.music_band_organizer.models.entities;

import com.anghel.music_band_organizer.utils.enums.State;
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
    @JoinColumn(name = "band_id")
    private Band rehearsalBand;
}
