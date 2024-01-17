# Music Band Organizer

Board regarding the music band organizer Java app.

* Project diagram:
[Entities](https://drive.google.com/file/d/1pJo-QXl8MyAoCpR701Aix5HOdSQgTnpu/view?usp=drive_link)
[LifeCycle](https://drive.google.com/file/d/12jib5PROZ1AalCXnY6fElMJSQpjZ1UDL/view?usp=drive_link)
[DBSchema](https://drive.google.com/file/d/1HKu5CslAn-rCSNPkNrxmfuEQMySnjbgX/view?usp=drive_link)

* API Documentation (Swagger):
[Music Band Organizer API Doccumentation](https://rhacp.github.io/MBO_Swagger/#/)

* Trello:
[Trello Music Band Organizer](https://trello.com/b/ciWN5OlZ/music-band-organizer)

---

A collaborative platform for musicians to meet, create bands, manage their repertoire and rehearsals, and release albums.

Musicians will be able to find other users, evaluate them based on their past experience, then message them to organize gigs and rehearsals.

---

### User

- users should be able to create accounts/ profiles with `name`, `description`, `birthday`, `age` and `past experience`;
- the `past experience` will include pairs of instrument and a short description to represent his current level;
- the `email` attribute should be unique;

---

### Band

- users should be able to create bands, then add other members;
- bands should have a dedicated chat;
- the `band_name` attribute should be unique;

---

### Rehearsal

- users part of a band should be able to plan rehearsals;
- rehearsals shuold have a band assigned, a starting date and time, a state (`DUE`, `DONE`) and a duration in hours;

---

### Post

- user should be able to release their music/ albums in posts that other users will be able to see;
- posts should include `title` and `description` and they should be linked to a band;
- possible integration with OpenAI: based on the `band_name`, `band_description` and `post_title`, the AI should be able to provide the band with an automatic basic description for the release;

---
