package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * interface SubjectRepository
 */
@Repository
interface SubjectRepository extends JpaRepository<Subject, Long> {
}
