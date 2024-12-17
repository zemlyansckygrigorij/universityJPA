package com.learn.universityjpa.repo;

import com.learn.universityjpa.annotations.SqlTest;
import com.learn.universityjpa.entity.Gender;
import com.learn.universityjpa.entity.Subject;
import com.learn.universityjpa.entity.Teacher;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
class TeacherComponentImplTest {

    @Autowired
    private TeacherComponent component;
    @Autowired
    private SubjectComponent subjectComponent;
    @DisplayName("1. Проверка подключения элемента component.")
    @Test
    public void checkTeacherComponentNotNull() {
        assertNotNull(component);
    }

    @DisplayName("2. Проверка поиска преподавателя по Id.")
    @SqlTest
    void findById() {
        Teacher teacher = component.findById(1L).orElseThrow();
        assertNotNull(teacher);
        assertEquals(1, teacher.getId());
        assertEquals("Oliver", teacher.getFirstName());
        assertEquals("Alexander", teacher.getSecondName());
        assertEquals("Williams", teacher.getLastName());
        assertEquals(Gender.MALE, teacher.getGender());
        assertEquals("first", teacher.getCategory());
        assertEquals("1999-03-02", new SimpleDateFormat("yyyy-MM-dd").format(teacher.getDateBirth()));
    }

    @DisplayName("3. Проверка поиска преподавателя по Id если такого преподавателя нет выкидывается исключение.")
    @SqlTest
    void findByIdOrDie() {
        assertThrows(PersonNotFoundException.class, ()-> component.findByIdOrDie(140L));
    }


    @DisplayName("4. Проверка поиска всех преподавателей.")
    @SqlTest
    void findAll() {
        assertEquals(9 , component.findAll().size());
    }

    @DisplayName("5. Проверка поиска преподавателя пол имени.")
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

    @DisplayName("6. Проверка удаления преподавателя по Id.")
    @SqlTest
    void deleteTeacherById() throws Exception {
        Teacher teacher = component.findByIdOrDie(1L);
        assertNotNull(teacher);
        component.deleteTeacherById(1L);
        Optional<Teacher> teacherNew = component.findById(1L);
        assertTrue(teacherNew.isEmpty());
    }

    @DisplayName("7. Проверка изменения данных преподавателя по id.")
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
                    scripts = "/db/sql/insertTeacher.sql ",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED))
    })
    void updateTeacherById() throws Exception {
        Date birth = new Date();
        Teacher teacher = new Teacher();
        teacher.setFirstName("testFirstName");
        teacher.setSecondName("testSecondName");
        teacher.setLastName("testLastName");
        teacher.setDateBirth(birth);
        teacher.setGender(Gender.FEMALE);
        teacher.setCategory("testCategory");
        component.updateTeacherById(1L, teacher);

        Teacher teacherNew = component.findByIdOrDie(1L);
        assertNotNull(teacherNew);
        assertEquals("testFirstName", teacherNew.getFirstName());
        assertEquals("testSecondName", teacherNew.getSecondName());
        assertEquals("testLastName", teacherNew.getLastName());
        assertEquals("FEMALE", teacherNew.getGender().toString());
        assertEquals("testCategory", teacherNew.getCategory());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd").format(birth), teacherNew.getDateBirth().toString());
    }

    @DisplayName("8. Проверка удаления преподавателя по Id.")
    @SqlTest
    void findAllSubjects() throws Exception {
        Teacher teacher = component.findByIdOrDie(1L);
        List<Subject> subjects = component.findAllSubjects(teacher);
        assertNotNull(subjects);
        assertEquals(4, subjects.size());
        assertTrue(subjects.contains(subjectComponent.findByIdOrDie(1L)));
        assertTrue(subjects.contains(subjectComponent.findByIdOrDie(3L)));
        assertTrue(subjects.contains(subjectComponent.findByIdOrDie(5L)));
        assertTrue(subjects.contains(subjectComponent.findByIdOrDie(7L)));
    }

    @DisplayName("9. Проверка удаления преподавателя по Id.")
    @SqlTest
    void checkSubject() throws Exception {
        Teacher teacher1 = component.findByIdOrDie(1L);
        Subject subject1 = subjectComponent.findByIdOrDie(1L);
        Teacher teacher2 = component.findByIdOrDie(2L);
        Subject subject2 = subjectComponent.findByIdOrDie(2L);
        assertTrue(component.checkSubject(teacher1, subject1));
        assertTrue(component.checkSubject(teacher2, subject2));
        assertFalse(component.checkSubject(teacher1, subject2));
        assertFalse(component.checkSubject(teacher2, subject1));
    }

    @DisplayName("10. Проверка удаления преподавателя по Id.")
    @SqlTest
    void addSubject() throws Exception {
        Teacher teacher1 = component.findByIdOrDie(1L);
        Subject subject2 = subjectComponent.findByIdOrDie(2L);
        Subject subject1 = subjectComponent.findByIdOrDie(1L);
        assertEquals(4, teacher1.getSubjects().size());
        assertFalse(component.checkSubject(teacher1, subject2));
        component.addSubject(teacher1, subject2);
        component.addSubject(teacher1, subject1);
        Teacher teacher1New = component.findByIdOrDie(1L);
        assertTrue(component.checkSubject(teacher1, subject2));
        assertEquals(5, teacher1New.getSubjects().size());
    }

    @DisplayName("11. Проверка удаления преподавателя по Id.")
    @SqlTest
    void deleteSubject() throws Exception {
        Teacher teacher1 = component.findByIdOrDie(1L);
        Subject subject1 = subjectComponent.findByIdOrDie(1L);
        Subject subject2 = subjectComponent.findByIdOrDie(2L);
        int count = teacher1.getSubjects().size();
        assertEquals(component.findByIdOrDie(1L).getSubjects().size(), 4);
        assertTrue(teacher1.getSubjects().contains(subject1));
        assertTrue(component.checkSubject(teacher1, subject1));
        assertFalse(teacher1.getSubjects().contains(subject2));

        Subject subjectDeleted = component.deleteSubject(teacher1, subject1);
        Subject subjectDeleted2 = component.deleteSubject(teacher1, subject2);
        assertEquals(subject1, subjectDeleted);
        assertEquals(subject2, subjectDeleted2);
        assertFalse(component.findByIdOrDie(1L).getSubjects().contains(subject1));
        assertEquals(component.findByIdOrDie(1L).getSubjects().size(), count - 1);
        assertFalse(component.checkSubject(teacher1, subject1));
    }
}
