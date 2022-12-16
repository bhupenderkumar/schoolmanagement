package com.school.repository;

import com.school.domain.Attendance;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface AttendanceRepositoryWithBagRelationships {
    Optional<Attendance> fetchBagRelationships(Optional<Attendance> attendance);

    List<Attendance> fetchBagRelationships(List<Attendance> attendances);

    Page<Attendance> fetchBagRelationships(Page<Attendance> attendances);
}
