package com.anghel.music_band_organizer.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_user")
    private String fromUser;

    @Column(name = "to_user")
    private String toUser;

    @Column(name = "message")
    private String message;

    @Column(name = "message_date")
    private LocalDate messageDate;

    @Column(name = "message_time")
    private LocalTime messageTime;
}
