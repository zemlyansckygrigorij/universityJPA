package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * interface StudentRepository
 */
@Repository
interface StudentRepository extends JpaRepository<Student, Long> {
}
