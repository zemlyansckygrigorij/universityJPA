package com.learn.universityjpa.repo;

import com.learn.universityjpa.annotations.SqlTest;

import com.learn.universityjpa.entity.*;

import com.learn.universityjpa.exceptions.PersonNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
class StudentComponentImplTest {

    @Autowired
    private StudentComponent component;
    @Autowired
    private GroupComponent groupComponent;

    @DisplayName("1. Проверка подключения элемента component.")
    @Test
    public void checkStudentComponentNotNull() {
        assertNotNull(component);
    }

    @DisplayName("2. Проверка поиска студента по Id.")
    @SqlTest
    void findById() {
        Optional<Student> studentOpt = component.findById(1L);
        assertTrue(studentOpt.isPresent());
        Student student = studentOpt.get();
        assertNotNull(student);
        assertEquals(1, student.getId());
        assertEquals("Bradley", student.getFirstName());
        assertEquals("Alexander", student.getSecondName());
        assertEquals("Abbe", student.getLastName());
        assertEquals(Gender.MALE, student.getGender());
        assertEquals(1, student.getGroup().getId());
        assertEquals("1999-03-02", new SimpleDateFormat("yyyy-MM-dd").format(student.getDateBirth()));
    }

    @DisplayName("3. Проверка поиска студента по Id если такого студента нет выкидывается исключение.")
    @SqlTest
    void findByIdOrDie() {
        assertThrows(PersonNotFoundException.class, ()-> component.findByIdOrDie(140L));
    }


    @DisplayName("4. Проверка поиска всех студентов.")
    @SqlTest
    void findAll() {
        assertEquals(72 , component.findAll().size());
    }

    @DisplayName("5. Проверка поиска группы по студенту.")
    @SqlTest
    void findGroup() throws Exception {
        Group group = groupComponent.findByIdOrDie(1L);
        Student student = component.findByIdOrDie(1L);

        assertEquals("Computer Science LEVEL first", group.getName());
        assertEquals("Bradley", student.getFirstName());
        assertEquals(group, component.findGroup(student));

    }

    @DisplayName("6. Проверка поиска всех студентов группы.")
    @SqlTest
    void findAllFromGroup() throws Exception {
        Group group = groupComponent.findByIdOrDie(1L);
        assertEquals(36, component.findAllFromGroup(group).size());
    }

    @DisplayName("7. Проверка поиска всех предметов данного студента.")
    @SqlTest
    void findAllSubjects() throws Exception {
        Student student = component.findByIdOrDie(1L);
        assertEquals(4, component.findAllSubjects(student).size());
    }

    @DisplayName("8. Проверка наличия предмета у данного студента.")
    @SqlTest
    void checkSubject() throws Exception {
        Student student = component.findByIdOrDie(1L);
        Student student2 = component.findByIdOrDie(2L);
        Subject subject = component.findAllSubjects(student).get(0);
        assertTrue(component.checkSubject(student, subject));
        assertFalse(component.checkSubject(student2, subject));
    }

    @DisplayName("9. Проверка поиска студента по имени.")
    @SqlTest
    void getStudentsByName() throws Exception {
        List<Student> students = component.getStudentsByName("Dav");
        assertEquals(2, students.size());
        assertEquals(72, component.findAll().size());
    }

    @DisplayName("10. Проверка удаления студента по id.")
    @Test
    @SqlGroup({
            @Sql(
                    scripts = "/db/sql/clean.sql ",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),
            @Sql(
                    scripts = "/db/sql/insert.sql ",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),
            @Sql(
                    scripts = "/db/sql/insertStudent.sql ",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED))
    })
    void deleteStudentById() throws Exception {
        int count = component.findAll().size();
        Student student = component.findByIdOrDie(1L);
        assertNotNull(student);
        component.deleteStudentById(1L);

        Optional<Student> studentNew = component.findById(1L);
        assertTrue(studentNew.isEmpty());
        assertEquals(count - 1, component.findAll().size());
    }

    @DisplayName("11. Проверка изменения данных студента по id.")
    @Test
    @SqlGroup({
            @Sql(
                    scripts = "/db/sql/clean.sql ",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),
            @Sql(
                    scripts = "/db/sql/insert.sql ",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),
            @Sql(
                    scripts = "/db/sql/insertStudent.sql ",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED))
    })
    void updateStudentById() throws Exception {
        Date birth = new Date();
        Student student = new Student();
        student.setFirstName("testFirstName");
        student.setSecondName("testSecondName");
        student.setLastName("testLastName");
        student.setDateBirth(birth);
        student.setGender(Gender.FEMALE);
        student.setGroup(groupComponent.findByIdOrDie(2L));
        component.updateStudentById(1L, student);

        Student studentNew = component.findByIdOrDie(1L);
        assertNotNull(studentNew);
        assertEquals("testFirstName", studentNew.getFirstName());
        assertEquals("testSecondName", studentNew.getSecondName());
        assertEquals("testLastName", studentNew.getLastName());
        assertEquals("FEMALE", studentNew.getGender().toString());
        assertEquals("2", studentNew.getGroup().getId().toString());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd").format(birth), studentNew.getDateBirth().toString());
    }

}
