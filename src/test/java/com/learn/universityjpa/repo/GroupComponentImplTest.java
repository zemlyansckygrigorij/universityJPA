package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Subject;

import com.learn.universityjpa.testAnnotation.SqlTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
class GroupComponentImplTest {
    @Autowired
    private GroupComponent component;
    @Autowired
    SubjectComponent subjectComponent;

    @DisplayName("Проверка подключения элемента component")
    @Test
    public void checkGroupComponent() {
        assertNotNull(component);
    }

    @DisplayName("Проверка подключения элемента subjectComponent")
    @Test
    public void checkSubjectComponent() {
        assertNotNull(subjectComponent);
    }

    @DisplayName("Проверка поиска группы по Id")
    @SqlTest
    void  findByIdTest() {
        assertEquals(2, component.findAll().size());
        Optional<Group> groupOpt =  component.findById(1L);
        Group group = groupOpt.get();
        assertNotNull(group);
        assertEquals(1, group.getId());
        assertEquals("Computer Science LEVEL first", group.getName());
    }

    @DisplayName("Проверка поиска группы по Id и выброс исключение если такой группы нет")
    @SqlTest
    void findByIdOrDieTest() {
        assertThrows(Exception.class, ()-> component.findByIdOrDie(4L));
    }

    @DisplayName("Проверка сохранения группы")
    @SqlTest
    void commitTest() {
        Group group = new Group();
        group.setSpecification("Specification");
        group.setName("Name");
        component.commit(group);
        assertEquals(3, component.findAll().size());
        List<Group> groups =component.findAll();
        assertTrue(groups.contains(group));
    }

    @DisplayName("Проверка поиска всех групп")
    @SqlTest
    void findAllTest() {
        assertEquals(2, this.component.findAll().size());
        Optional<Group> groupOpt =  component.findById(1L);
        Group group1 = component.findById(1L).get();
        assertEquals("Computer Science LEVEL first", group1.getName());
        Group group2 = component.findById(2L).get();
        assertEquals("Computer Science LEVEL second", group2.getName());
    }

    @DisplayName("Проверка поиска всех предметов данной группы")
    @SqlTest
    void findAllSubjectsTest() {
        assertEquals(7, this.subjectComponent.findAll().size());
        Group group1 = component.findById(1L).get();
        assertNotNull(group1);
        assertEquals("Computer Science LEVEL first", group1.getName());

        Set<Subject> subjects = group1.getSubjects();
        assertEquals(4, subjects.size());

        Group group2 = component.findById(2L).get();
        assertNotNull(group2);
        assertEquals("Computer Science LEVEL second", group2.getName());
        Set<Subject> subjects2 = group2.getSubjects();
        assertEquals(3, subjects2.size());
    }

    @DisplayName("Проверка поиска группы по имени")
    @SqlTest
    void findByNameTest() throws Exception {
        Group group = component.findByName("LEVEL first").get(0);
        assertNotNull(group);
        assertEquals(1, group.getId());
    }

    @DisplayName("Проверка поиска группы по предметам ")
    @SqlTest
    void findBySubjectsTest() throws Exception {
        Subject subject = subjectComponent.findAll().get(0);
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        List<Group> groups = component.findBySubjects(subjects);
        assertEquals(1, groups.size());
        assertEquals( groups.get(0), component.findByIdOrDie(1L));
    }

    @DisplayName("Проверка наличия предмета в расписании группы ")
    @SqlTest
    void checkSubjectTest() throws Exception {
        Group group = component.findByIdOrDie(1L);
        Subject subject1 = subjectComponent.findByIdOrDie(1L);
        Subject subject2 = subjectComponent.findByIdOrDie(2L);
        assertTrue(component.checkSubject(group, subject1));
        assertFalse(component.checkSubject(group, subject2));
    }

    @DisplayName("Проверка добавления предмета в расписания группы ")
    @SqlTest
    void addSubjectTest() throws Exception {
        Group group = component.findByIdOrDie(1L);
        Subject subject = subjectComponent.findByIdOrDie(2L);
        assertFalse(component.checkSubject(group, subject));
        component.addSubject(group, subject);
        assertTrue(component.checkSubject(group, subject));
    }

    @DisplayName("Проверка удаления предмета из расписания группы ")
    @SqlTest
    void deleteSubjectTest() throws Exception {
        Group group = component.findByIdOrDie(1L);
        Subject subject = subjectComponent.findByIdOrDie(1L);
        assertTrue(component.checkSubject(group, subject));
        component.deleteSubject(group, subject);
        assertFalse(component.checkSubject(group, subject));
    }
}