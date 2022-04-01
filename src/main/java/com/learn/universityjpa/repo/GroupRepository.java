package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Group;

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
            + "from groups  "
            + "where name  LIKE %:nameGroup% ",
            nativeQuery = true)
    Optional<List<Group>> findByName(@Param("nameGroup")String nameGroup);

    @Query(value
            = "select * from groups " +
            "where id in (select group_id from group_subject where subject_id in (:ids))",
            nativeQuery = true)
    Optional<List<Group>> findBySubjects(@Param("ids") long[] ids);
}
