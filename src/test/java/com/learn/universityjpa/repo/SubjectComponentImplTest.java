package com.learn.universityjpa.repo;

import com.learn.universityjpa.annotations.SqlTest;
import com.learn.universityjpa.entity.Subject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
class SubjectComponentImplTest {

    @Autowired
    private SubjectComponent component;
    @Autowired
    private GroupComponent groupComponent;

    @DisplayName("Проверка подключения элемента component")
    @Test
    public void checkSubjectComponentNotNull() {
        assertNotNull(component);
        assertNotNull(groupComponent);
    }

    @DisplayName("Проверка поиска предмета по Id")
    @SqlTest
    void findById() {
        Subject subject = component.findById(1L).get();
        assertNotNull(subject);
        assertEquals("Introduction to Computational Science and Engineering", subject.getName());
        assertTrue(subject.getDescription()
                .contains("the same pedagogy as 6.0002 (Introduction to Computational Thinking"));
    }

    @DisplayName("Проверка поиска предмета по Id если такого предмета нет выкидывается исключение")
    @SqlTest
    void findByIdOrDie() {
        assertThrows(Exception.class, ()-> component.findByIdOrDie(140L));
    }


    @DisplayName("Проверка поиска всех предметов")
    @SqlTest
    void findAll() {
        assertEquals(7, component.findAll().size());
    }

    @DisplayName("Проверка поиска предмета пол имени")
    @SqlTest
    void getSubjectsByName() throws Exception {
        List<Subject> subjects = component.getSubjectsByName("Algebra");
        assertEquals(1, subjects.size());
        Subject subject = subjects.get(0);
        assertEquals("Linear Algebra and Optimization", subject.getName());
        assertTrue(subject.getDescription()
                .contains(" same fundamental concepts as 18.06 with a view toward modeling,"));
    }
}
