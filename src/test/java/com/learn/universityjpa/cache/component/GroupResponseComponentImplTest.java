package com.learn.universityjpa.cache.component;

import com.learn.universityjpa.cache.repo.GroupResponseRepository;
import com.learn.universityjpa.controller.model.response.GroupResponse;
import com.learn.universityjpa.db.component.GroupComponent;
import com.learn.universityjpa.db.entity.Group;
import com.learn.universityjpa.db.entity.Subject;
import com.learn.universityjpa.exceptions.GroupNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
class GroupResponseComponentImplTest {
    @Autowired
    private  GroupResponseComponent component;
    @Autowired
    private GroupResponseRepository repo;
    @Test
    void findById() {
        GroupResponse groupResponse = component.findById(1L).orElseThrow();
        assertEquals("GroupResponse(id=1, name=testName3, specification=testSpecification3, subjects=[], students=[])", groupResponse.toString());
    }

    @Test
    void findByIdOrDie() {
        assertThrows(Exception.class, ()-> component.findByIdOrDie(10L));
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
        GroupResponse groupResponse = component.findById(1L).orElseThrow();
        assertEquals(4, groupResponse.getSubjects().size());
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