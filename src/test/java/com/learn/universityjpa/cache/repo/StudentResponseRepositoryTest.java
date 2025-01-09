package com.learn.universityjpa.cache.repo;

import com.learn.universityjpa.db.component.SubjectComponent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration

class StudentResponseRepositoryTest {
    @Autowired
    StudentResponseRepository repo;
    @DisplayName("1. Проверка подключения элемента component.")
    @Test
    public void checkStudentResponseRepositoryt() {
        assertNotNull(repo);
    }
    @DisplayName("2. Проверка подключения элемента component.")
    @Test
    public void checkStudentResponseRepositoryHasData() {
        assertEquals(repo.count(),1);
    }
}