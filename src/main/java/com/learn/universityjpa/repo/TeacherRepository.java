package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * interface TeacherRepository
 */
@Repository
interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
