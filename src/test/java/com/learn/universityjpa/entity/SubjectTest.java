package com.learn.universityjpa.entity;


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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
class SubjectTest {
    @Autowired
    SubjectComponent subjectComponent;

    @Test
    @Sql(
            scripts = "/db/sql/entity/Subject/clean.sql",
            executionPhase = AFTER_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED)
    )
    public void makeTest() {

        long count = subjectComponent.findAll().size();

        Subject subject = new Subject();
        subject.setDescription("Description");
        subject.setName("Name");


        subjectComponent.commit(subject);

        Subject subjectFrom = subjectComponent.findAll().get(0);
        assertEquals(subject, subjectFrom);
        assertEquals(subjectFrom.getDescription(), "Description");
        assertEquals(subjectFrom.getName(), "Name");
        assertEquals(count + 1, subjectComponent.findAll().size());
    }
    @Test
    public void shouldNotAllowNullDescription() {
        Subject subject = new Subject();
        subject.setDescription(null);
        subject.setName("Name");
        assertThrows(Exception.class, () -> subjectComponent.commit(subject));
    }
    @Test
    public void shouldNotAllowNullName() {
        Subject subject = new Subject();
        subject.setName(null);
        subject.setDescription("Description");
        assertThrows(Exception.class, () -> subjectComponent.commit(subject));
    }
}
