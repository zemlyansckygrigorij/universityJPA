package com.learn.universityjpa.entity;

import com.learn.universityjpa.repo.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
class TeacherTest {
    @Autowired
    TeacherRepository repo;
    @Test
    public void makeTest(){
        long count = repo.findAll().size();
        Teacher teacher = new Teacher();
        teacher.setFirstName("FirstName");
        teacher.setSecondName("SecondName");
        teacher.setLastName("LastName");
        teacher.setGender((Gender.MALE));
        Date birth = new Date();
        teacher.setDateBirth(birth);
        teacher.setCategory("First");
        repo.save(teacher);
        Teacher teacherFrom = repo.findAll().get((int) count);
        assertEquals(teacher, teacherFrom);
        assertEquals(teacherFrom.getFirstName(), "FirstName");
        assertEquals(teacherFrom.getSecondName(), "SecondName");
        assertEquals(teacherFrom.getLastName(), "LastName");
        assertEquals(teacherFrom.getGender(), Gender.MALE);
        assertEquals(teacherFrom.getDateBirth(), birth);
        assertEquals(teacherFrom.getCategory(), "First");
        assertEquals(count+1, repo.findAll().size());
    }
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
        assertThrows(Exception.class,()->repo.save(teacher));
    }
}