# Music Band Organizer

Music band organizer app.

* Project diagram:
[Entities](https://drive.google.com/file/d/1pJo-QXl8MyAoCpR701Aix5HOdSQgTnpu/view?usp=drive_link)
[LifeCycle](https://drive.google.com/file/d/12jib5PROZ1AalCXnY6fElMJSQpjZ1UDL/view?usp=drive_link)
[DBSchema](https://drive.google.com/file/d/1HKu5CslAn-rCSNPkNrxmfuEQMySnjbgX/view?usp=drive_link)

* API Documentation (Swagger):
[Music Band Organizer API Doccumentation](https://rhacp.github.io/MBO_Swagger/#/)

* Trello:
[Trello Music Band Organizer](https://trello.com/b/ciWN5OlZ/music-band-organizer)

---

### Tech Stack


* Java 21
* Spring Boot 3.2.1
* Maven
* Hibernate
* PostgreSQL
* Lombok
* Mockito
* Gmail SMTP
* Swagger

---

### Helpers

PostgreSQL DB:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/DATABASE_NAME
spring.datasource.username=DATABASE_USERNAME
spring.datasource.password=DATABASE_PASSWORD
spring.datasource.driver-class-name=org.postgresql.Driver
```

OpenAI:
```
api.url=https://api.openai.com/v1/chat/completions
api.key=API_KEY
api.model=gpt-3.5-turbo-1106
```

Gmail SMTP:
```
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=MAIL_USERNAME
spring.mail.password=MAIL_PASSWORD
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

---

This project is meant to be a soial platform for musicians. They can create profiles, search for other musicians according to multiple criteria and send each other messages, eventually forming a band.

The band will be an isolated group of musicians. Within it, members will have three roles (OWNER, ADMIN and MEMBER) and will be able to easily plan and organize rehearsals and releases for songs, EPs or albums that will later be seen by the rest of the platform members.

Rehearsals can be private (only band members will participate) or public (the rest of the users will see the time and date of the rehearsal and will be able to participate).

The releases will be in the form of a post that the rest of the users will see in the application (in a newsfeed in my view). These will give the band (which creates the post) the opportunity to create a description for the post using ChatGTP based on the name of the band, the description of the band and the name of the recording they will post.

Within the application, members will be able to send each other messages and see previous conversations in chronological order.

At the same time, users will be notified by email when they are added to a band or when a rehearsal is created.

---

User:
- users should be able to create accounts/ profiles with `name`, `description`, `birthday`, `age` and `past experience`;
- the `past experience` will include pairs of instrument and a short description to represent his current level;
- the `email` attribute should be unique;

Band:
- users should be able to create bands, then add other members;
- bands should have a dedicated chat;
- the `band_name` attribute should be unique;

Rehearsal:
- users part of a band should be able to plan rehearsals;
- rehearsals shuold have a band assigned, a starting date and time, a state (`DUE`, `DONE`) and a duration in hours;
- Rehearsal can be public or private. Other users can see and attend to open rehearsals;

Post:
- user should be able to release their music/ albums in posts that other users will be able to see;
- posts should include `title` and `description` and they should be linked to a band;
- possible integration with OpenAI: based on the `band_name`, `band_description` and `post_title`, the AI should be able to provide the band with an automatic basic description for the release;

Message:
- messages should include a feature to get an entire conversation;

---
