package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;


@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query(value
            = "select g "
            + "from Group g "
            + "where g.name LIKE %:name% ")
    Optional<List<Group>> findByName(@Param("name")String name);

    @Query(value
            = "select * from groups " +
            "where id in (select group_id from group_subject where subject_id in (:ids))",
            nativeQuery = true)
    Optional<List<Group>> findBySubjects(@Param("ids") long[] ids);

    @Modifying
    @Transactional // @Modifying annotation should be wrapped up with @Transactional
    @Query("update Group g set g.name = ?1 , g.specification = ?2  where g.id = ?3")
    void updateGroupById(String name, String specification, Long id);
}
