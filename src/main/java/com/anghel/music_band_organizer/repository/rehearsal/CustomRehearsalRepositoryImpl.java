package com.anghel.music_band_organizer.repository.rehearsal;

import com.anghel.music_band_organizer.models.entities.Rehearsal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomRehearsalRepositoryImpl implements CustomRehearsalRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Rehearsal> findFilteredRehearsal(Long rehearsalId, LocalDate rehearsalDate, LocalDateTime rehearsalTime) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Rehearsal> cq = cb.createQuery(Rehearsal.class);

        Root<Rehearsal> rehearsal = cq.from(Rehearsal.class);
        List<Predicate> predicates = new ArrayList<>();

        if (rehearsalId != null) {
            predicates.add(cb.equal(rehearsal.get("id"), rehearsalId));
        }
        if (rehearsalDate != null) {
            predicates.add(cb.equal(rehearsal.get("rehearsal_date"), rehearsalDate));
        }
        if (rehearsalTime != null) {
            predicates.add(cb.equal(rehearsal.get("rehearsal_time"), rehearsalTime));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }
}
