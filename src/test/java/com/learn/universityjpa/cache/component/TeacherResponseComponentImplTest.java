package com.learn.universityjpa.cache.component;

import com.learn.universityjpa.cache.repo.TeacherResponseRepository;
import com.learn.universityjpa.controller.model.response.TeacherResponse;
import com.learn.universityjpa.db.component.TeacherComponent;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
class TeacherResponseComponentImplTest {
    @Autowired
    private TeacherComponent component;
    @Autowired
    private TeacherResponseRepository repo;
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
    void getTeachersByName() {
    }

    @Test
    void deleteTeacherById() {
    }

    @Test
    void updateTeacherById() {
    }

    @Test
    void findAllSubjects() {
    }

    @Test
    void checkSubject() {
    }

    @Test
    void addSubject() {
    }

    @Test
    void deleteSubject() {
    }

    @Test
    void testDeleteSubject() {
    }

    @Test
    void testAddSubject() {
    }
    @Test
    void insertAllSubject() {
        repo.deleteAll();
        component.findAll().forEach(t-> repo.save(new TeacherResponse(t)));
        assertEquals(repo.count(), 12);
    }
}
