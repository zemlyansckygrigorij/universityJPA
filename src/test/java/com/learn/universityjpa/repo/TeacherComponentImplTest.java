package com.learn.universityjpa.repo;

import com.learn.universityjpa.annotations.SqlTest;
import com.learn.universityjpa.entity.Gender;
import com.learn.universityjpa.entity.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
class TeacherComponentImplTest {

    @Autowired
    private TeacherComponent component;

    @DisplayName("Проверка подключения элемента component")
    @Test
    public void checkTeacherComponentNotNull() {
        assertNotNull(component);
    }

    @DisplayName("Проверка поиска преподавателя по Id")
    @SqlTest
    void findById() {
        Teacher teacher = component.findById(1L).get();
        assertNotNull(teacher);
        assertEquals(1, teacher.getId());
        assertEquals("Oliver", teacher.getFirstName());
        assertEquals("Alexander", teacher.getSecondName());
        assertEquals("Williams", teacher.getLastName());
        assertEquals(Gender.MALE, teacher.getGender());
        assertEquals("first", teacher.getCategory());
    }

    @DisplayName("Проверка поиска преподавателя по Id если такого преподавателя нет выкидывается исключение")
    @SqlTest
    void findByIdOrDie() {
        assertThrows(Exception.class, ()-> component.findByIdOrDie(140L));
    }


    @DisplayName("Проверка поиска всех преподавателей")
    @SqlTest
    void findAll() {
        assertEquals(9 , component.findAll().size());
    }

    @DisplayName("Проверка поиска преподавателя пол имени")
    @SqlTest
    void getTeachersByName() throws Exception {
        Teacher teacher = component.getTeachersByName("Harry").get(0);
        assertEquals(2, teacher.getId());
        assertEquals("Harry", teacher.getFirstName());
        assertEquals("Quinn", teacher.getSecondName());
        assertEquals("Peters", teacher.getLastName());
        assertEquals(Gender.MALE, teacher.getGender());
        assertEquals("Second", teacher.getCategory());
    }
}
