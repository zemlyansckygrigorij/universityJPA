package com.learn.universityjpa.db.repo;

import com.learn.universityjpa.config.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
class UserComponentImplTest {

    @Autowired
    UserComponent component;
    @Test
    void findByIdOrDie() {
    }

    @Test
    void commit() {
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteUserById() {
    }

    @Test
    void updateUserById() {
    }

 /*   @Test
    void sendDataToUser() throws Exception {
        User user1 =  new User();
        user1.setName("Anthony");
        user1.setPassword("$2a$05$MjLgSZSY7ap9zQyTq7RlsOOU9OmFLIq.Qb8s53CPJt3yWYuvicA/O");
        user1.setRole("ADMIN");

        User user2 =  new User();
        user2.setName("Austin");
        user2.setPassword("Austin");
        user2.setRole("USER");

        User user3 =  new User();
        user3.setName("Brandon");
        user3.setPassword("Brandon");
        user3.setRole("USER");

        component.commit(user1);
        component.commit(user2);
        component.commit(user3);
        assertTrue(true);
    }*/
}

