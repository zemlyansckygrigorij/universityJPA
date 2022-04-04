package com.learn.universityjpa.entity;

import com.learn.universityjpa.repo.GroupComponent;
import com.learn.universityjpa.repo.SubjectComponent;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
class GroupTest {

    @Autowired
    private GroupComponent component;
    @Autowired
    SubjectComponent subjectComponent;
    @Test
    public void checkGroupComponent() {
        assertNotNull(component);
    }

    @Test
    @Sql(
            scripts = "/db/sql/entity/Group/clean-up.sql",
            executionPhase = AFTER_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED)
    )
    public void insertTest() {
        Subject subject = new Subject();
        subject.setDescription("Description");
        subject.setName("Name");
        subjectComponent.commit(subject);
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);


        long count = component.findAll().size();
        assertEquals(count, 0);
        Group group = new Group();
        group.setSpecification("Specification");
        group.setName("Name");
        group.setSubjects(subjects);
        component.commit(group);
        Group groupFrom = component.findAll().get(0);
        assertEquals(groupFrom.getSpecification(), "Specification");
        assertEquals(groupFrom.getName(), "Name");
        assertTrue(groupFrom.getSubjects().contains(subject));
        assertEquals(count + 1, component.findAll().size());
    }

    @Test
    public void shouldNotAllowNullSpecification() {
        Group group = new Group();
        group.setName("Group");
        group.setSpecification(null);
        assertThrows(Exception.class, ()-> component.commit(group));
    }

    @Test
    public void shouldNotAllowNullName() {
        Group group = new Group();
        group.setName(null);
        group.setSpecification("Specification");
        assertThrows(Exception.class, ()-> component.commit(group));
    }
}
