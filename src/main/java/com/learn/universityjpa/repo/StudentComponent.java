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
     * Находит всех студентов данной группы.
     *
     * @return список студентов .
     */
    List<Student> findGroup(Group group);

    /**
     * Находит всех группы.
     *
     * @return список групп .
     */
    List<Group> findAll();
    /**
     * Находит все предметы данной группы.
     *
     * @return список предметов .
     */
    List<Subject> findAllSubjects(Group group);

    /**
     * Проверяет наличие студента данной группы.
     *
     * @param  student студент.
     * @return наличие студента в группе
     */
    boolean checkStudent(Group group ,Student student);

    /**
     * Проверяет наличие студента данной группы.
     *
     * @param  subject предмет.
     * @return наличие предмета в группе
     */
    boolean checkSubject(Group group ,Subject subject);

    /**
     * Добавляет предмет в данную группу.
     * @param  group группа .
     * @param  subject предмет
     */
    Subject addSubject(Group group, Subject subject);

    /**
     * Удаляет предмет из данной группы.
     *
     * @param  group группа .
     * @param  subject предмет
     */
    Subject deleteSubject(Group group, Subject subject);

    /**
     * Добавляет студента в данную группу.
     *
     * @param  group группа .
     * @param  student студент
     */
    Student addStudent(Group group, Student student);

    /**
     *  Удаляет  студента из данную группу.
     *
     * @param  group группа .
     * @param  student студент
     */
    Student deleteStudent(Group group, Student student);
}
