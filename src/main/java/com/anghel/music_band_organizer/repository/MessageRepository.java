package com.anghel.music_band_organizer.repository;

import com.anghel.music_band_organizer.models.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Message findMessageByMessageDateAndMessageTime(LocalDate messageDate, LocalTime messageTime);

    Message findMessageById(Long messageId);
}
