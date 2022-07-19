package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Gender;
import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;




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
            + "where group_id = :groupId ",
            nativeQuery = true)
    Optional<List<Student>> getStudentsFromGroup(@Param("groupId") Long groupId);

    @Query("SELECT s FROM Student s " +
            "WHERE s.firstName LIKE %:name% " +
            "or s.secondName LIKE %:name% " +
            "or s.lastName LIKE %:name%")
    Optional<List<Student>> getStudentsByName(@Param("name") String name);

    @Modifying
    @Transactional // @Modifying annotation should be wrapped up with @Transactional
    @Query("update Student s set " +
            " s.firstName = ?2, " +
            " s.secondName = ?3," +
            " s.lastName = ?4," +
            " s.gender = ?5 ," +
            " s.group = ?6, " +
            " s.dateBirth = ?7 " +
            "  where s.id = ?1 ")
    int updateStudentById(
            Long id,
            String firstName,
            String secondName,
            String lastName,
            Gender gender,
            Group group,
            Date dateBirth
           );
}
