package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Subject;
import com.learn.universityjpa.entity.Teacher;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * Компонент работы с данными преподавателей .
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
     * @param id идентификатор преподавателя.
     * @return преподаватель.
     */
    Teacher findByIdOrDie(Long id) throws Exception;

    /**
     * Сохраняет преподавателя.
     *
     * @param teacher преподаватель для сохранения.
     * @return сохраненный преподаватель.
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
     * @return список преподавателей.
     */
    List<Teacher> getTeachersByName(String name) throws Exception;

    /**
     * Удалить преподавателя по по идентификатору.
     *
     * @param id идентификатор преподавателя.
     */
    void deleteTeacherById(Long id);

    /**
     * Обновить преподавателя по по идентификатору.
     *
     * @param id идентификатор преподавателя.
     * @param  teacher преподаватель.
     */
    void updateTeacherById(Long id, Teacher teacher);

    /**
     * Находит все предметы данного преподавателя.
     *
     * @return список предметов.
     */
    List<Subject> findAllSubjects(Teacher teacher);

    /**
     * Проверяет наличие предмета у данного преподавателя.
     *
     * @param  subject предмет.
     * @return наличие предмета у данного преподавателя.
     */
    boolean checkSubject(Teacher teacher, Subject subject);

    /**
     * Добавляет предмет преподавателю.
     * @param  teacher преподаватель.
     * @param  subject предмет.
     */
    Subject addSubject(Teacher teacher, Subject subject);

    /**
     * Удаляет предмет у преподавателя.
     *
     * @param  teacher преподаватель.
     * @param  subject предмет.
     */
    Subject deleteSubject(Teacher teacher, Subject subject) throws ParseException;

    /**
     * Удаляет предмет у преподавателя.
     *
     * @param  idTeacher идентификатор преподавателя.
     * @param  idSubject идентификатор предмета.
     */
    Subject deleteSubject(long idTeacher, long idSubject) throws Exception;

    /**
     * Добавляет предмет преподавателю.
     *
     * @param  idTeacher идентификатор преподавателя.
     * @param  idSubject идентификатор предмета.
     */
    Subject addSubject(long idTeacher, long idSubject) throws Exception;
}
