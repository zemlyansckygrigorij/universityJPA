package com.learn.universityjpa.entity;

import com.learn.universityjpa.repo.TeacherComponent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
class TeacherTest {
    @Autowired
    TeacherComponent component;

    @DisplayName("1. Проверка вставки преподавателя.")
    @Test
    public void makeTest() {
        long count = component.findAll().size();
        Teacher teacher = new Teacher();
        teacher.setFirstName("FirstName");
        teacher.setSecondName("SecondName");
        teacher.setLastName("LastName");
        teacher.setGender((Gender.MALE));
        Date birth = new Date();
        teacher.setDateBirth(birth);
        teacher.setCategory("First");
        component.commit(teacher);
        Teacher teacherFrom = component.findAll().get((int) count);
        assertEquals(teacher, teacherFrom);
        assertEquals(teacherFrom.getFirstName(), "FirstName");
        assertEquals(teacherFrom.getSecondName(), "SecondName");
        assertEquals(teacherFrom.getLastName(), "LastName");
        assertEquals(teacherFrom.getGender(), Gender.MALE);
        assertEquals(teacherFrom.getDateBirth(), birth);
        assertEquals(teacherFrom.getCategory(), "First");
        assertEquals(count + 1, component.findAll().size());
    }

    @DisplayName("2. Проверка вставки преподавателя c отсутствием имени.")
    @Test
    public void shouldNotAllowNullFirstName() {
        Teacher teacher = new Teacher();
        teacher.setFirstName(null);
        teacher.setSecondName("SecondName");
        teacher.setLastName("LastName");
        teacher.setGender((Gender.MALE));
        Date birth = new Date();
        teacher.setDateBirth(birth);
        teacher.setCategory("First");
        assertThrows(Exception.class, ()->component.commit(teacher));
    }

    @DisplayName("3. Проверка вставки преподавателя c отсутствием отчества.")
    @Test
    public void shouldNotAllowNullSecondName() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("FirstName");
        teacher.setSecondName(null);
        teacher.setLastName("LastName");
        teacher.setGender((Gender.MALE));
        Date birth = new Date();
        teacher.setDateBirth(birth);
        teacher.setCategory("First");
        assertThrows(Exception.class, ()->component.commit(teacher));
    }
    @DisplayName("4. Проверка вставки преподавателя c отсутствием фамилии.")
    @Test
    public void shouldNotAllowNullLastName() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("FirstName");
        teacher.setSecondName("SecondName");
        teacher.setLastName(null);
        teacher.setGender((Gender.MALE));
        Date birth = new Date();
        teacher.setDateBirth(birth);
        teacher.setCategory("First");
        assertThrows(Exception.class, ()->component.commit(teacher));
    }

    @DisplayName("2. Проверка вставки преподавателя c отсутствием даты рождения.")
    @Test
    public void shouldNotAllowNullDateBirth() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("FirstName");
        teacher.setSecondName("SecondName");
        teacher.setLastName("LastName");
        teacher.setGender((Gender.MALE));
        teacher.setDateBirth(null);
        teacher.setCategory("First");
        assertThrows(Exception.class, ()->component.commit(teacher));
    }

    @DisplayName("2. Проверка вставки преподавателя c отсутствием пола.")
    @Test
    public void shouldNotAllowNullGender() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("FirstName");
        teacher.setSecondName("SecondName");
        teacher.setLastName("LastName");
        teacher.setGender((null));
        Date birth = new Date();
        teacher.setDateBirth(birth);
        teacher.setCategory("First");
        assertThrows(Exception.class, ()->component.commit(teacher));
    }
}
