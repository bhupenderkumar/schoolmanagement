package com.school.repository;

import com.school.domain.Parent;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Parent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {}
