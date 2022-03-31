package com.learn.universityjpa.entity;

import com.learn.universityjpa.repo.GroupComponent;
import com.learn.universityjpa.repo.StudentComponent;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
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

    @Test
    @Sql(
            scripts = "/db/sql/entity/Student/insert.sql",
            executionPhase = BEFORE_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED)
    )
    public void makeTest() {
        assertNotNull(studentComponent);
        assertNotNull(groupComponent);
        Group group = groupComponent.findAll().get(0);
        assertEquals(group.getId(), 1);
        assertEquals(group.getName(), "GroupTest");
        assertEquals(group.getSpecification(), "DescriptionTest");
        Date birth = new Date();
        Student student = new Student();
        student.setDateBirth(birth);
        student.setFirstName("Jhon");
        student.setGender(Gender.MALE);
        student.setSecondName("Frank");
        student.setLastName("Kennedy");
        student.setId(2L);
        student.setGroup(group);
        assertEquals(studentComponent.findAll().size(), 0);
        studentComponent.commit(student);
        assertEquals(studentComponent.findAll().size(), 1);
        Student studentFromTable = studentComponent.findAll().get(0);
        assertNotNull(studentFromTable);
        assertEquals(studentFromTable.getGroup(), group);
        assertEquals(studentFromTable.getFirstName(), "Jhon");
        assertEquals(studentFromTable.getSecondName(), "Frank");
        assertEquals(studentFromTable.getLastName(), "Kennedy");
        assertEquals(studentFromTable.getGender(), Gender.MALE);
    }

    @Test
    public void shouldNotAllowNullGroup() {
        Date birth = new Date();
        Student student = new Student();
        student.setDateBirth(birth);
        student.setFirstName("Jhon");
        student.setGender(Gender.MALE);
        student.setSecondName("Frank");
        student.setLastName("Kennedy");
        student.setId(2L);
        student.setGroup(null);
        assertThrows(Exception.class, ()-> studentComponent.commit(student));
    }
}
