package com.learn.universityjpa.cache.component;


import com.learn.universityjpa.cache.repo.SubjectResponseRepository;
import com.learn.universityjpa.controller.model.response.SubjectResponse;
import com.learn.universityjpa.db.component.SubjectComponent;
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
class SubjectResponseComponentImplTest {
    @Autowired
    private SubjectComponent component;
    @Autowired
    private SubjectResponseRepository repo;
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
    }

    @Test
    void getSubjectsByName() {
    }

    @Test
    void deleteSubjectById() {
    }

    @Test
    void updateSubjectById() {
    }
        @Test
    void insertAllSubject() {
        repo.deleteAll();
        component.findAll().forEach(s->{
          //  System.out.println(s.toString());
            repo.save(new SubjectResponse(s));
        });
        assertEquals(repo.count(),12);
    }
}