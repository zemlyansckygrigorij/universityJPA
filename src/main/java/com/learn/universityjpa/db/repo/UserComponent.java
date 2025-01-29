package com.learn.universityjpa.db.repo;

import com.learn.universityjpa.config.User;

import java.util.List;

public interface UserComponent {
    /**
     * Ищет пользователя по идентификатору и падает по ошибке, если не нашел.
     *
     * @param id идентификатор пользователя.
     * @return пользователь.
     */
    User findByIdOrDie(Long id);

    /**
     * Сохраняет пользователя.
     *
     * @param user пользователь для сохранения.
     * @return сохраненный пользователь.
     */
    User commit(User user) throws Exception;

    /**
     * Находит всех пользователей.
     *
     * @return список пользователей.
     */
    List<User> findAll();

    /**
     * Удалить пользователя по идентификатору.
     *
     * @param id идентификатор пользователя.
     */
    void deleteUserById(Long id);

    /**
     * Обновить пользователя по идентификатору.
     *
     * @param id идентификатор пользователя.
     * @param  user пользователь.
     */
    void updateUserById(Long id, User user);
}
