package com.learn.universityjpa.cache.component;

import com.learn.universityjpa.cache.repo.GroupResponseRepository;
import com.learn.universityjpa.db.component.GroupComponent;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
class GroupResponseComponentImplTest {
    @Autowired
    private GroupComponent component;
    @Autowired
    private GroupResponseRepository repo;
    @Test
    void findById() {
    }

    @Test
    void findByIdOrDie() {
    }

    @Test
    void commit() {
    }

    @Test
    void findAll() {

        assertEquals(repo.count(),12);
    }

    @Test
    void findAllSubjectsByGroupId() {
    }

    @Test
    void checkSubjectByGroupId() {
    }

    @Test
    void addSubject() {
    }

    @Test
    void deleteSubject() {
    }

    @Test
    void findAllStudentsByGroupId() {
    }

    @Test
    void findByName() {
    }

    @Test
    void findBySubjects() {
    }

    @Test
    void deleteGroupById() {
    }

    @Test
    void updateGroupById() {
    }

 /*   @Test
    void insertAllGroup() {
        repo.deleteAll();
        component.findAll().forEach(group->repo.save(new GroupResponse(group)));
        assertEquals(repo.count(),12);
    }*/
}