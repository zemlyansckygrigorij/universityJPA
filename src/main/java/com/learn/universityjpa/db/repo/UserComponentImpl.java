package com.learn.universityjpa.db.repo;

import com.learn.universityjpa.config.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class UserComponentImpl
 */

@Component
public class UserComponentImpl implements UserComponent{
    @Autowired
    UserRepo repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Optional<User> findById(Long id) {
        return this.repo.findById(id);
    }
    @Override
    public User findByIdOrDie(Long id) {
        return null;
    }

    @Override
    public User commit(User user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("user.getPassword() - "+user.getPassword());
        return repo.save(user);
    }

    @Override
    public List<User> findAll() {
        return this.repo.findAll();
    }

    @Override
    public void deleteUserById(Long id) {

    }

    @Override
    public void updateUserById(Long id, User user) {

    }
}
