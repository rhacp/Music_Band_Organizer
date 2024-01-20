package com.anghel.music_band_organizer.repository.user;

import com.anghel.music_band_organizer.models.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomUserRepositoryImpl implements CustomUserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findFilteredUser(Long userId, String firstName, String lastName, String email, LocalDate birthday, String pastExperience, String stageName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> user = cq.from(User.class);
        List<Predicate> predicates = new ArrayList<>();

        if (userId != null) {
            predicates.add(cb.equal(user.get("id"), userId));
        }
        if (firstName != null) {
            predicates.add(cb.equal(user.get("firstName"), firstName));
        }
        if (lastName != null) {
            predicates.add(cb.equal(user.get("lastName"), lastName));
        }
        if (birthday != null) {
            predicates.add(cb.equal(user.get("birthday"), birthday));
        }
        if (email != null) {
            predicates.add(cb.equal(user.get("email"), email));
        }
        if (stageName != null) {
            predicates.add(cb.equal(user.get("stageName"), stageName));
        }
// not working... but solved in the UserServiceImpl under getFilteredUsers
//        if (pastExperience != null) {
//            predicates.add(cb.isMember(pastExperience, user.get("pastExperience")));
//        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }
}
