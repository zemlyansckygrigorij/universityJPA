package com.learn.universityjpa.entity;

import com.learn.universityjpa.repo.GroupComponent;
import com.learn.universityjpa.repo.StudentComponent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
class StudentTest {

    @Autowired
    private StudentComponent studentComponent;
    @Autowired
    private GroupComponent groupComponent;

    @DisplayName("1. Проверка вставки студента.")
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
    public void makeTest() throws Exception {

        Group group = groupComponent.findAll().get(0);
        Date birth = new Date();
        Student student = new Student();
        student.setDateBirth(birth);
        student.setFirstName("Jhon1");
        student.setGender(Gender.MALE);
        student.setSecondName("Frank1");
        student.setLastName("Kennedy1");

        student.setGroup(group);
        assertEquals(studentComponent.findAll().size(), 72);
        studentComponent.commit(student);
        assertEquals(studentComponent.findAll().size(), 73);
        Student studentFromTable = studentComponent.getStudentsByName("Jhon1").get(0);
        assertNotNull(studentFromTable);
        assertEquals(studentFromTable.getGroup(), group);
        assertEquals(studentFromTable.getFirstName(), "Jhon1");
        assertEquals(studentFromTable.getSecondName(), "Frank1");
        assertEquals(studentFromTable.getLastName(), "Kennedy1");
        assertEquals(studentFromTable.getGender(), Gender.MALE);
    }

    @DisplayName("2. Проверка вставки студента c отсутствием группы.")
    @Test
    public void shouldNotAllowNullGroup() {
        Date birth = new Date();
        Student student = new Student();
        student.setDateBirth(birth);
        student.setFirstName("Jhon");
        student.setGender(Gender.MALE);
        student.setSecondName("Frank");
        student.setLastName("Kennedy");
        assertThrows(DataIntegrityViolationException.class, ()-> studentComponent.commit(student));
    }

    @DisplayName("3. Проверка вставки студента c отсутствием имени.")
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
    public void shouldNotAllowNullFirstName() {
        Group group = groupComponent.findAll().get(0);
        Date birth = new Date();
        Student student = new Student();
        student.setDateBirth(birth);
        student.setGender(Gender.MALE);
        student.setSecondName("Frank");
        student.setLastName("Kennedy");
        student.setId(2L);
        student.setGroup(group);
        studentComponent.commit(student);
        assertThrows(DataIntegrityViolationException.class, ()-> studentComponent.getStudentsByName("Kennedy").get(0));

    }

    @DisplayName("4. Проверка вставки студента c отсутствием отчества.")
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
    public void shouldNotAllowNullSecondName() {
        Group group = groupComponent.findAll().get(0);
        Date birth = new Date();
        Student student = new Student();
        student.setDateBirth(birth);
        student.setFirstName("Jhon");
        student.setGender(Gender.MALE);
        student.setLastName("Kennedy");
        student.setId(2L);
        student.setGroup(group);
        studentComponent.commit(student);
        assertThrows(DataIntegrityViolationException.class,
                ()-> studentComponent.getStudentsByName("Kennedy").get(0));
    }
    @DisplayName("5. Проверка вставки студента c отсутствием фамилии.")
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
    public void shouldNotAllowNullLastName() {
        Group group = groupComponent.findAll().get(0);
        Date birth = new Date();
        Student student = new Student();
        student.setDateBirth(birth);
        student.setFirstName("Jhon");
        student.setGender(Gender.MALE);
        student.setSecondName("Frank");
        student.setId(2L);
        student.setGroup(group);
        studentComponent.commit(student);
        assertThrows(DataIntegrityViolationException.class,
                ()-> studentComponent.getStudentsByName("Frank").get(0));
    }

    @DisplayName("6. Проверка вставки студента c отсутствием пола.")
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
    public void shouldNotAllowNullGender() {
        Group group = groupComponent.findAll().get(0);
        Date birth = new Date();
        Student student = new Student();
        student.setDateBirth(birth);
        student.setFirstName("Jhon");
        student.setSecondName("Frank");
        student.setLastName("Kennedy");
        student.setId(2L);
        student.setGroup(group);
        studentComponent.commit(student);
        assertThrows(DataIntegrityViolationException.class,
                ()-> studentComponent.getStudentsByName("Frank").get(0));
    }

    @DisplayName("7. Проверка вставки студента c отсутствием даты рождения.")
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
    public void shouldNotAllowNullDateBirth() {
        Group group = groupComponent.findAll().get(0);
        Student student = new Student();
        student.setFirstName("Jhon");
        student.setGender(Gender.MALE);
        student.setSecondName("Frank");
        student.setLastName("Kennedy");
        student.setId(2L);
        student.setGroup(group);
        studentComponent.commit(student);
        assertThrows(DataIntegrityViolationException.class,
                ()-> studentComponent.getStudentsByName("Frank").get(0));
    }

}
