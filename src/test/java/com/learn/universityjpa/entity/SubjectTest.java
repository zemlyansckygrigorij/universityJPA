package com.learn.universityjpa.entity;

import com.learn.universityjpa.repo.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
class SubjectTest {
    @Autowired
    SubjectRepository repo;
    @Test
    public void makeTest(){
        long count = repo.findAll().size();
        Subject subject = new Subject();
        subject.setDescription("Description");
        subject.setName("Name");
        repo.save(subject);
        Subject subjectFrom = repo.findAll().get(0);
        assertEquals(subject, subjectFrom);
        assertEquals(subjectFrom.getDescription(), "Description");
        assertEquals(subjectFrom.getName(), "Name");
        assertEquals(count+1, repo.findAll().size());
    }
    @Test
    public void shouldNotAllowNullDescription() {
        Subject subject = new Subject();
        subject.setDescription(null);
        subject.setName("Name");
        assertThrows(Exception.class,()->repo.save(subject));
    }
    @Test
    public void shouldNotAllowNullName() {
        Subject subject = new Subject();
        subject.setName(null);
        subject.setDescription("Name");
        assertThrows(Exception.class,()->repo.save(subject));
    }
}