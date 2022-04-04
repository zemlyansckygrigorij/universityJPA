package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Student;
import com.learn.universityjpa.entity.Subject;

import java.util.List;
import java.util.Optional;

/**
 * Компонент работы с данными студентов.
 */
public interface StudentComponent {
    /**
     * Ищет студента по идентификатору.
     *
     * @param id идентификатор студента.
     * @return студента.
     */
    Optional<Student> findById(Long id);

    /**
     * Находит всех студентов.
     *
     * @return список студентов .
     */
    List<Student> findAll();

    /**
     * Ищет студента по идентификатору и падает по ошибке, если не нашел.
     *
     * @param id идентификатор студента.
     * @return студента.
     */
    Student findByIdOrDie(Long id) throws Exception;

    /**
     * Сохраняет студента.
     *
     * @param student студент для сохранения.
     * @return сохраненная студент.
     */
    Student commit(Student student);

    /**
     * Находит  группу данного студента.
     *
     * @return группу.
     */
    Group findGroup(Student student);

    /**
     * Находит  список предметов данного студента.
     *
     * @return Находит  список предметов.
     */
    List<Subject> findAllSubjects(Student student);

    /**
     * Проверяет наличие предмета  студента .
     *
     * @param  subject предмет.
     * @return наличие предмета в группе
     */
    boolean checkSubject(Student student, Subject subject);

    /**
     * Проверяет наличие предмета  студента .
     *
     * @param group предмет.
     * @return наличие предмета в группе
     */
    List<Student> findAllFromGroup(Group group) throws Exception;
}
