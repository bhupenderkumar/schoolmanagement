package com.school.repository;

import com.school.domain.Attendance;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class AttendanceRepositoryWithBagRelationshipsImpl implements AttendanceRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Attendance> fetchBagRelationships(Optional<Attendance> attendance) {
        return attendance.map(this::fetchStudents);
    }

    @Override
    public Page<Attendance> fetchBagRelationships(Page<Attendance> attendances) {
        return new PageImpl<>(fetchBagRelationships(attendances.getContent()), attendances.getPageable(), attendances.getTotalElements());
    }

    @Override
    public List<Attendance> fetchBagRelationships(List<Attendance> attendances) {
        return Optional.of(attendances).map(this::fetchStudents).orElse(Collections.emptyList());
    }

    Attendance fetchStudents(Attendance result) {
        return entityManager
            .createQuery(
                "select attendance from Attendance attendance left join fetch attendance.students where attendance is :attendance",
                Attendance.class
            )
            .setParameter("attendance", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Attendance> fetchStudents(List<Attendance> attendances) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, attendances.size()).forEach(index -> order.put(attendances.get(index).getId(), index));
        List<Attendance> result = entityManager
            .createQuery(
                "select distinct attendance from Attendance attendance left join fetch attendance.students where attendance in :attendances",
                Attendance.class
            )
            .setParameter("attendances", attendances)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
