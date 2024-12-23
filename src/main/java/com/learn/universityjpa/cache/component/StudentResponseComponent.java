package com.learn.universityjpa.cache.component;

import com.learn.universityjpa.controller.model.response.StudentResponse;
import com.learn.universityjpa.db.entity.Group;
import com.learn.universityjpa.db.entity.Student;
import com.learn.universityjpa.db.entity.Subject;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * Компонент работы с данными студентов.
 */
public interface StudentResponseComponent {
    /**
     * Ищет студента по идентификатору.
     *
     * @param id идентификатор студента.
     * @return студента.
     */
    Optional<StudentResponse> findById(Long id);

    /**
     * Находит всех студентов.
     *
     * @return список студентов.
     */
    List<StudentResponse> findAll();

    /**
     * Ищет студента по идентификатору и падает по ошибке, если не нашел.
     *
     * @param id идентификатор студента.
     * @return студент.
     */
    StudentResponse findByIdOrDie(Long id) throws Exception;

    /**
     * Сохраняет студента.
     *
     * @param student студент для сохранения.
     * @return сохраненный студент.
     */
    StudentResponse commit(Student student);

    /**
     * Удалить студента по идентификатору.
     *
     * @param id идентификатор студента.
     */
    void deleteStudentById(Long id) throws Exception;

    /**
     * Обновить студента по идентификатору.
     *
     * @param id идентификатор группы.
     * @param  student студент.
     */
    void updateStudentById(Long id, Student student) throws ParseException;
}
