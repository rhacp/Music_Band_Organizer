package com.anghel.music_band_organizer.services.mail;

import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.models.entities.Mail;
import com.anghel.music_band_organizer.models.entities.Rehearsal;
import com.anghel.music_band_organizer.models.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Profile("RHACP")
@Slf4j
@Service
public class MailServiceImpl implements MailService{

    @Value("${spring.mail.username}")
    private String senderAddress;

    private final JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(String mailTarget, Mail mail, String methodName) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(senderAddress);
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mail.getMessage());
        simpleMailMessage.setTo(mailTarget);

        javaMailSender.send(simpleMailMessage);
        log.info("Email sent. Method: {}.", methodName);
    }

    @Override
    public Mail prepareMailMessage(User user, String methodName) {
        Mail mail = new Mail();
        mail.setSubject("You have a new message!");
        mail.setMessage("You received a message from " + user.getFirstName() + " " + user.getLastName() + ".");
        log.info("Email created. Method: {}.", methodName);

        return mail;
    }

    public Mail prepareMailRehearsal(Rehearsal rehearsal, String methodName) {
        Mail mail = new Mail();
        mail.setSubject("Your band set a rehearsal!");
        mail.setMessage("Your band set a new " + rehearsal.getRehearsalAvailability().getAvailabilityLabel() + " rehearsal on " + rehearsal.getRehearsalDate() + ", at " + rehearsal.getRehearsalTime() + ". Be there on time!");
        log.info("Email created. Method: {}.", methodName);

        return mail;
    }

    public Mail prepareMailBand(Band band, String methodName) {
        Mail mail = new Mail();
        mail.setSubject("Your are now member of a band!");
        mail.setMessage("You are now part of the band " + band.getBandName() + ". You can now get to know the other members. You will be notified when rehearsals are planned via email.");
        log.info("Email created. Method: {}.", methodName);

        return mail;
    }
}
