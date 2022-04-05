package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Teacher;
import java.util.List;
import java.util.Optional;

/**
 * Компонент работы с данными преподавателями .
 */
public interface TeacherComponent {
    /**
     * Ищет преподавателя по идентификатору.
     *
     * @param id идентификатор преподавателя.
     * @return преподаватель.
     */
    Optional<Teacher> findById(Long id);

    /**
     * Ищет преподавателя по идентификатору и падает по ошибке, если не нашел.
     *
     * @param id идентификаторпреподавателя.
     * @return преподаватель.
     */
    Teacher findByIdOrDie(Long id) throws Exception;

    /**
     * Сохраняет преподавателя.
     *
     * @param teacher преподаватель для сохранения.
     * @return сохраненная преподаватель.
     */
    Teacher commit(Teacher teacher);

    /**
     * Находит всех преподавателей.
     *
     * @return список преподавателей .
     */
    List<Teacher> findAll();

    /**
     * Находит всех преподавателей по введеному имени.
     *
     * @return список студентов .
     */
    List<Teacher> getTeachersByName(String name) throws Exception;

}
