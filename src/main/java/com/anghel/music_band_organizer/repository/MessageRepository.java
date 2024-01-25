package com.anghel.music_band_organizer.repository;

import com.anghel.music_band_organizer.models.entities.Message;
import com.anghel.music_band_organizer.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Message findMessageByMessageDateAndMessageTime(LocalDate messageDate, LocalTime messageTime);

    Message findMessageById(Long messageId);

    List<Message> findMessageByFromUserAndToUser(User fromUser, User toUser);

    List<Message> findMessageByFromUserOrFromUserAndToUserOrToUserOrderByMessageDate(User fromUser, User secondFromUser, User toUser, User secondToUser);

    List<Message> findMessageByFromUserAndToUserOrFromUserAndToUserOrderByMessageDate(User fromUser, User secondFromUser, User toUser, User secondToUser);
}
