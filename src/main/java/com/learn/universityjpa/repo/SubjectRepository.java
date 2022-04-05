package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * interface SubjectRepository
 */
@Repository
interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query(value
            = "select * "
            + "from subject  "
            + "where subject.name LIKE %:nameSubject%",
            nativeQuery = true)
    Optional<List<Subject>> getSubjectsByName(@Param("nameSubject") String nameSubject);
}
