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
