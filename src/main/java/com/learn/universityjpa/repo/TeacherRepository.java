package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * interface TeacherRepository
 */
@Repository
interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT t FROM  Teacher t " +
            "WHERE t.firstName LIKE %:name% " +
            "or t.secondName LIKE %:name% " +
            "or t.lastName LIKE %:name%")
    Optional<List<Teacher>> getTeachersByName(@Param("name") String name);
}
