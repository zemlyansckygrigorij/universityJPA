package com.learn.universityjpa.repo;

import com.learn.universityjpa.annotations.SqlTest;
import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Subject;
import com.learn.universityjpa.exceptions.GroupHasStudentsException;
import com.learn.universityjpa.exceptions.GroupNotFoundException;
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
import java.util.ArrayList;
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
class GroupComponentImplTest {
    @Autowired
    private GroupComponent component;
    @Autowired
    SubjectComponent subjectComponent;

    @DisplayName("1. Проверка подключения элемента component.")
    @Test
    public void checkGroupComponent() {
        assertNotNull(component);
    }

    @DisplayName("2. Проверка подключения элемента subjectComponent.")
    @Test
    public void checkSubjectComponent() {
        assertNotNull(subjectComponent);
    }

    @DisplayName("3. Проверка поиска группы по Id.")
    @SqlTest
    void  findByIdTest() {
        assertEquals(2, component.findAll().size());
        Optional<Group> groupOpt =  component.findById(1L);
        assertTrue( groupOpt.isPresent());
        Group group = groupOpt.orElseThrow();
        assertNotNull(group);
        assertEquals(1, group.getId());
        assertEquals("Computer Science LEVEL first", group.getName());
    }

    @DisplayName("4. Проверка поиска группы по Id и выброс исключение если такой группы нет.")
    @SqlTest
    void findByIdOrDieTest() {
        assertThrows(GroupNotFoundException.class, ()-> component.findByIdOrDie(4L));
    }

    @DisplayName("5. Проверка сохранения группы.")
    @SqlTest
    void commitTest() {
        Group group = new Group();
        group.setSpecification("Specification");
        group.setName("Name");
        component.commit(group);
        assertEquals(3, component.findAll().size());
        List<Group> groups = component.findAll();
        assertTrue(groups.contains(group));
    }

    @DisplayName("6. Проверка поиска всех групп.")
    @SqlTest
    void findAllTest() {
        assertEquals(2, this.component.findAll().size());
        Optional<Group> groupOpt1 = component.findById(1L);
        assertTrue(groupOpt1.isPresent());
        Group group1 = groupOpt1.get();
        assertEquals("Computer Science LEVEL first", group1.getName());
        Optional<Group> groupOpt2 = component.findById(2L);
        assertTrue(groupOpt2.isPresent());
        Group group2 = groupOpt2.get();
        assertEquals("Computer Science LEVEL second", group2.getName());
    }

    @DisplayName("7. Проверка поиска всех предметов данной группы.")
    @SqlTest
    void findAllSubjectsTest() {
        assertEquals(7, this.subjectComponent.findAll().size());
        Optional<Group> groupOpt1 = component.findById(1L);
        assertTrue(groupOpt1.isPresent());
        Group group1 = groupOpt1.get();
        assertEquals("Computer Science LEVEL first", group1.getName());

        List<Subject> subjects = group1.getSubjects();
        assertEquals(4, subjects.size());

        Optional<Group> groupOpt2 = component.findById(2L);
        assertTrue(groupOpt2.isPresent());
        Group group2 = groupOpt2.get();
        assertEquals("Computer Science LEVEL second", group2.getName());
        List<Subject> subjects2 = group2.getSubjects();
        assertEquals(3, subjects2.size());
    }

    @DisplayName("8. Проверка поиска группы по имени.")
    @SqlTest
    void findByNameTest() throws Exception {
        Group group = component.findByName("LEVEL first").get(0);
        assertNotNull(group);
        assertEquals(1, group.getId());
    }

    @DisplayName("9. Проверка поиска группы по предметам.")
    @SqlTest
    void findBySubjectsTest() throws Exception {
        Subject subject = subjectComponent.findAll().get(0);
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        List<Group> groups = component.findBySubjects(subjects);
        assertEquals(1, groups.size());
        assertEquals(groups.get(0), component.findByIdOrDie(1L));
    }

    @DisplayName("10. Проверка наличия предмета в расписании группы.")
    @SqlTest
    void checkSubjectTest() throws Exception {
        Group group = component.findByIdOrDie(1L);
        Subject subject1 = subjectComponent.findByIdOrDie(1L);
        Subject subject2 = subjectComponent.findByIdOrDie(2L);
        assertTrue(component.checkSubject(group, subject1));
        assertFalse(component.checkSubject(group, subject2));
    }

    @DisplayName("11. Проверка добавления предмета в расписания группы.")
    @SqlTest
    void addSubjectTest() throws Exception {
        Group group = component.findByIdOrDie(1L);
        Subject subject = subjectComponent.findByIdOrDie(2L);
        assertFalse(component.checkSubject(group, subject));
        component.addSubject(group, subject);
        assertTrue(component.checkSubject(group, subject));
    }

    @DisplayName("12. Проверка удаления предмета из расписания группы.")
    @SqlTest
    void deleteSubjectTest() throws Exception {
        Group group = component.findByIdOrDie(1L);
        Subject subject = subjectComponent.findByIdOrDie(1L);
        assertTrue(component.checkSubject(group, subject));
        component.deleteSubject(group, subject);
        assertFalse(component.checkSubject(group, subject));
    }

    @DisplayName("13. Проверка изменения группы.")
    @Test
    @SqlGroup({
             @Sql(
                scripts = "/db/sql/insert.sql ",
                executionPhase = BEFORE_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED))
    })
    void updateGroupByIdTest() throws Exception {
        Group group = component.findByIdOrDie(1L);
        group.setName("TestName2");
        group.setSpecification("TestSpecification2");
        component.updateGroupById(group.getId(), group);
        Group groupNew = component.findByIdOrDie(1L);
        assertEquals(groupNew.getName(), "TestName2");
        assertEquals(groupNew.getSpecification(), "TestSpecification2");
   }

    @DisplayName("14. Проверка удаления группы.")
    @SqlTest
    void deleteGroupById() throws Exception {
        assertThrows(GroupHasStudentsException.class, ()-> component.deleteGroupById(1L));

        Group group = new Group();
        group.setSpecification("Specification");
        group.setName("Name");
        component.commit(group);
        assertEquals(3, component.findAll().size());
        Optional<Group> groupOpt = component.findByName("Name").stream().findFirst();
        assertTrue(groupOpt.isPresent());

        long id = groupOpt.get().getId();
        component.deleteGroupById(id);
        assertEquals(2, component.findAll().size());
        Optional<Group> groupOptNew = component.findByName("Name").stream().findFirst();
        assertTrue(groupOptNew.isEmpty());
    }

    @DisplayName("15. заполнение данными.")
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
                    scripts = "/db/sql/insertStudent.sql ",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),

            @Sql(
                    scripts = "/db/sql/insertGS.sql ",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),
            @Sql(
                    scripts = "/db/sql/insertTeacher.sql ",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),
            @Sql(
            scripts = "/db/sql/insertTS.sql ",
            executionPhase = BEFORE_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED))
    })
    void insertTest()  {
        assertNotNull(component);
    }
    @DisplayName("16. Создание базы данных.")
    @Test
    @Sql(
            scripts = "/db/sql/create.sql ",
            executionPhase = BEFORE_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED))
    void createTableTest()  {
        assertNotNull(component);
    }
}
