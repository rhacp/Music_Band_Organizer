package com.anghel.music_band_organizer.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "message_date")
    private LocalDate messageDate;

    @Column(name = "message_time")
    private LocalTime messageTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_user", referencedColumnName = "id")
    @JsonBackReference(value = "toUser")
    private User toUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_user", referencedColumnName = "id")
    @JsonBackReference(value = "fromUser")
    private User fromUser;
}
