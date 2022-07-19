package com.learn.universityjpa.repo;

import com.learn.universityjpa.annotations.SqlTest;
import com.learn.universityjpa.entity.Subject;

import com.learn.universityjpa.exceptions.SubjectNotFoundException;
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

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
class SubjectComponentImplTest {

    @Autowired
    private SubjectComponent component;
    @Autowired
    private GroupComponent groupComponent;

    @DisplayName("1. Проверка подключения элемента component.")
    @Test
    public void checkSubjectComponentNotNull() {
        assertNotNull(component);
        assertNotNull(groupComponent);
    }

    @DisplayName("2. Проверка поиска предмета по Id.")
    @SqlTest
    void findById() {
        Subject subject = component.findById(1L).get();
        assertNotNull(subject);
        assertEquals("Introduction to Computational Science and Engineering", subject.getName());
        assertTrue(subject.getDescription()
                .contains("the same pedagogy as 6.0002 (Introduction to Computational Thinking"));
    }

    @DisplayName("3. Проверка поиска предмета по Id если такого предмета нет выкидывается исключение.")
    @SqlTest
    void findByIdOrDie() {
        assertThrows(SubjectNotFoundException.class, ()-> component.findByIdOrDie(140L));
    }


    @DisplayName("4. Проверка поиска всех предметов.")
    @SqlTest
    void findAll() {
        assertEquals(7, component.findAll().size());
    }

    @DisplayName("5. Проверка поиска предмета пол имени.")
    @SqlTest
    void getSubjectsByName() throws Exception {
        List<Subject> subjects = component.getSubjectsByName("Algebra");
        assertEquals(1, subjects.size());
        Subject subject = subjects.get(0);
        assertEquals("Linear Algebra and Optimization", subject.getName());
        assertTrue(subject.getDescription()
                .contains(" same fundamental concepts as 18.06 with a view toward modeling,"));
    }

    @DisplayName("6. Проверка поиска предмета пол имени.")
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
                    scripts = "/db/sql/insertSubject.sql ",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),
            @Sql(
                    scripts = "/db/sql/insertGS.sql ",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),
    })
    void updateSubjectById() throws ParseException {
        Subject subject = component.findById(1L).get();
        assertNotNull(subject);
        assertEquals("Introduction to Computational Science and Engineering", subject.getName());
        assertTrue(subject.getDescription()
                .contains("the same pedagogy as 6.0002 (Introduction to Computational Thinking"));

        subject.setName("testName");
        subject.setDescription("testtDescription");
        component.updateSubjectById(1L, subject);
        Subject subjectNew = component.findById(1L).get();
        assertNotNull(subjectNew);
        assertEquals("testName", subjectNew.getName());
        assertEquals("testtDescription", subjectNew.getDescription());
    }

    @DisplayName("7. Проверка удаления предмета по Id.")
    @SqlTest
    void deleteSubjectById() throws Exception {
        int count = component.findAll().size();
        Subject subject = component.findByIdOrDie(1L);
        assertNotNull(subject);
        component.deleteSubjectById(1L);

        Optional<Subject> subjectNew = component.findById(1L);
        assertTrue(subjectNew.isEmpty());
    }
}
