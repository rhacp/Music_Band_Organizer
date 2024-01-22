package com.anghel.music_band_organizer.models.entities;

import com.anghel.music_band_organizer.utils.enums.Availability;
import com.anghel.music_band_organizer.utils.enums.State;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "rehearsals")
public class Rehearsal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rehearsal_state")
    private State rehearsalState;

    @Column(name = "rehearsal_date")
    private LocalDate rehearsalDate;

    @Column(name = "rehearsal_time")
    private LocalTime rehearsalTime;

    @Column(name = "rehearsal_duration_hours")
    private Integer rehearsalDurationHours;

    @Column(name = "rehearsal_availability")
    private Availability rehearsalAvailability;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rehersal_band_id")
    @JsonBackReference(value = "rehearsal")
    private Band rehearsalBand;
}
