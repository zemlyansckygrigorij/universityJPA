package com.learn.universityjpa.entity;

import com.learn.universityjpa.annotations.SqlTest;
import com.learn.universityjpa.repo.GroupComponent;
import com.learn.universityjpa.repo.SubjectComponent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
class GroupTest {

    @Autowired
    private GroupComponent component;
    @Test
    public void checkGroupComponent() {
        assertNotNull(component);
    }

    @DisplayName("1. Проверка вставки группы.")
    @SqlTest
    public void insertTest() throws Exception {
        Group group = new Group();
        group.setSpecification("Specification");
        group.setName("Name");
        component.commit(group);
        Group groupFrom = component.findByName("Name").get(0);
        assertEquals(groupFrom.toString(), group.toString());
    }

    @DisplayName("2. Проверка вставки группы без спецификации.")
    @Test
    public void shouldNotAllowNullSpecification() {
        Group group = new Group();
        group.setName("Group");
        group.setSpecification(null);
        assertThrows(Exception.class, ()-> component.commit(group));
    }

    @DisplayName("3. Проверка вставки группы без имени.")
    @Test
    public void shouldNotAllowNullName() {
        Group group = new Group();
        group.setName(null);
        group.setSpecification("Specification");
        assertThrows(Exception.class, ()-> component.commit(group));
    }
}
