package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    // выбрать всех студентов данной группы
    @Query("select st from Student st where st.id_group =:groupId ")
    List<Student> findAllStudentFromGroupByid(Long groupId);

/*
    @Query("select us from Group us inner join ReferalEntity ref on us.id = ref.partner.id and ref.referal.id = :userId order by us.id nulls first")
    UserEntity findPartnerByReferalId(Long userId);*/
    // найти предметы данной группы*
}
