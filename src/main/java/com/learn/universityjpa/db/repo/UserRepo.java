package com.learn.universityjpa.db.repo;

import com.learn.universityjpa.config.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * interface UserRepo
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByName(String username);
}
