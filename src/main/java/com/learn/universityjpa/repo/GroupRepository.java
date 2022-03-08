package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Student;
import com.learn.universityjpa.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface GroupRepository extends JpaRepository<Group, Long> {
    @Query(value
            = "select * "
            + "from student "
            + "where student.id = :studentId and "
            + " student.id_group = :groupId ",
            nativeQuery = true)
    Optional<List<Student>> getStudentByIdFromGroup(@Param("studentId") Long studentId, @Param("groupId") Long groupId);
    @Query(value
            = "select * "
            + "from subject as s inner join group_subject as g "
            + "     on s.id_subject = g.id_subject "
            + "where g.id_subject = :subjectId and "
            + " g.id_group = :groupId ",
            nativeQuery = true)
    Optional<List<Subject>> getSubjectByIdFromGroup(@Param("subjectId") Long subjectId, @Param("groupId") Long groupId);
}
