package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * interface StudentRepository
 */
@Repository
interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value
            = "select * "
            + "from student  "
            + "where id_group = :groupId ",
            nativeQuery = true)
    Optional<List<Student>> getStudentsFromGroup(@Param("groupId") Long groupId);

    @Query("SELECT s FROM Student s " +
            "WHERE s.firstName LIKE %:name% " +
            "or s.secondName LIKE %:name% " +
            "or s.lastName LIKE %:name%")
    Optional<List<Student>> getStudentsByName(@Param("name") String name);
}
