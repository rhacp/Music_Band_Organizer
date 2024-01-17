# Music Band Organizer

Board regarding the music band organizer Java app.

* Project diagram:
[Music Band Organizer Diagram](https://lucid.app/lucidchart/94a110f5-a10b-438f-80d6-9f54654effd5/edit?beaconFlowId=48404C42688A9D3D&invitationId=inv_274f7007-4cc1-463a-b292-27add0246363&page=0_0#)

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
