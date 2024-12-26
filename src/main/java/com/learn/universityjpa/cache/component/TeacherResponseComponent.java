package com.learn.universityjpa.cache.component;

import com.learn.universityjpa.controller.model.response.SubjectResponse;
import com.learn.universityjpa.controller.model.response.TeacherResponse;
import com.learn.universityjpa.db.entity.Subject;
import com.learn.universityjpa.db.entity.Teacher;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * Компонент работы с данными преподавателей.
 */
public interface TeacherResponseComponent {
    /**
     * Ищет преподавателя по идентификатору.
     *
     * @param id идентификатор преподавателя.
     * @return преподаватель.
     */
    Optional<TeacherResponse> findById(Long id);

    /**
     * Ищет преподавателя по идентификатору и падает по ошибке, если не нашел.
     *
     * @param id идентификатор преподавателя.
     * @return преподаватель.
     */
    TeacherResponse findByIdOrDie(Long id) throws Exception;

    /**
     * Сохраняет преподавателя.
     *
     * @param teacher преподаватель для сохранения.
     * @return сохраненный преподаватель.
     */
    TeacherResponse commit(Teacher teacher);

    /**
     * Находит всех преподавателей.
     *
     * @return список преподавателей.
     */
    List<TeacherResponse> findAll();

    /**
     * Находит всех преподавателей по введенному имени.
     *
     * @return список преподавателей.
     */
    List<TeacherResponse> getTeachersByName(String name) throws Exception;

    /**
     * Удалить преподавателя по идентификатору.
     *
     * @param id идентификатор преподавателя.
     */
    void deleteTeacherById(Long id);

    /**
     * Обновить преподавателя по идентификатору.
     *
     * @param id      идентификатор преподавателя.
     * @param teacher преподаватель.
     */
    void updateTeacherById(Long id, Teacher teacher);

    /**
     * Находит все предметы данного преподавателя.
     *
     * @return список предметов.
     */
    List<SubjectResponse> findAllSubjects(Teacher teacher);

    /**
     * Проверяет наличие предмета у данного преподавателя.
     *
     * @param subject предмет.
     * @return наличие предмета у данного преподавателя.
     */
    boolean checkSubject(Teacher teacher, Subject subject);

    /**
     * Добавляет предмет преподавателю.
     *
     * @param teacher преподаватель.
     * @param subject предмет.
     */
    SubjectResponse addSubject(Teacher teacher, Subject subject);

    /**
     * Удаляет предмет у преподавателя.
     *
     * @param teacher преподаватель.
     * @param subject предмет.
     */
    SubjectResponse deleteSubject(Teacher teacher, Subject subject) throws ParseException;

    /**
     * Удаляет предмет у преподавателя.
     *
     * @param idTeacher идентификатор преподавателя.
     * @param idSubject идентификатор предмета.
     */
    SubjectResponse deleteSubject(long idTeacher, long idSubject) throws Exception;

    /**
     * Добавляет предмет преподавателю.
     *
     * @param idTeacher идентификатор преподавателя.
     * @param idSubject идентификатор предмета.
     */
    SubjectResponse addSubject(long idTeacher, long idSubject) throws Exception;
}
