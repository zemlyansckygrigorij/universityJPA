package com.learn.universityjpa.entity;

import com.learn.universityjpa.repo.GroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
class GroupTest {

    @Autowired
    GroupRepository repo;

    @Test
    public void makeTest(){
        Group group = new Group();
        group.setId(1L);
        group.setName("Group");
        group.setSpecification("Specification");
        Student student = new Student();
        List<Student> list = new ArrayList<>() ;
        list.add(student);
        group.setStudents(list);

        assertEquals(group.getId(), 1);
        assertEquals(group.getName(), "Group");
        assertEquals(group.getSpecification(), "Specification");
        assertEquals(group.getStudents().get(0), student);
        Group group1 = new Group();

        assertNull(group1.getId());
        assertNull(group1.getName());
        assertNull(group1.getSpecification());
        assertNull(group1.getStudents());

    }
    @Test
    public void insertTest(){
       long count = repo.findAll().size();
        Group group = new Group();
        group.setSpecification("Specification");
        group.setName("Name");

        repo.save(group);
        Group groupFrom = repo.findAll().get(0);
        assertEquals(group, groupFrom);
        assertEquals(groupFrom.getSpecification(), "Specification");
        assertEquals(groupFrom.getName(), "Name");
        assertEquals(count+1, repo.findAll().size());
    }
    @Test
    public void shouldNotAllowNullSpecification() {
        Group group = new Group();
        group.setName("Group");
        group.setSpecification(null);
        assertThrows(Exception.class,()->repo.save(group));
    }
    @Test
    public void shouldNotAllowNullName() {
        Group group = new Group();
        group.setName(null);
        group.setSpecification("Specification");
        assertThrows(Exception.class,()->repo.save(group));
    }
}