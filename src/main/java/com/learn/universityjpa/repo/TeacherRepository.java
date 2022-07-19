package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Gender;
import com.learn.universityjpa.entity.Teacher;

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
 * interface TeacherRepository
 */
@Repository
interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT t FROM  Teacher t " +
            "WHERE t.firstName LIKE %:name% " +
            "or t.secondName LIKE %:name% " +
            "or t.lastName LIKE %:name%")
    Optional<List<Teacher>> getTeachersByName(@Param("name") String name);

    @Modifying
    @Transactional // @Modifying annotation should be wrapped up with @Transactional
    @Query("update Teacher t  set " +
            "t.firstName = ?1," +
            " t.secondName = ?2," +
            " t.lastName = ?3," +
            " t.dateBirth = ?4," +
            " t.category = ?5," +
            " t.gender = ?6" +
            "  where t.id = ?7")
    int updateTeacherById(
            String firstName,
            String secondName,
            String lastName,
            Date dateBirthday,
            String category,
            Gender gender,
            Long id
    );
}
