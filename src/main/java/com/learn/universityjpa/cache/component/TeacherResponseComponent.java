package com.learn.universityjpa.cache.component;

import com.learn.universityjpa.controller.model.json.SubjectJson;
import com.learn.universityjpa.controller.model.response.TeacherResponse;
import com.learn.universityjpa.db.entity.Teacher;
import java.util.List;
import java.util.Optional;

/**
 * Компонент работы с данными преподавателей  сохраняемые в кэше.
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
    List<SubjectJson>  findAllSubjects(Long id) throws Exception;

    /**
     * Проверяет наличие предмета у данного преподавателя.
     *
     * @param teacherId идентификатор преподавателя.
     * @param subjectId идентификатор предмета.
     * @return наличие предмета у данного преподавателя.
     */
    boolean checkSubject(Long teacherId , Long subjectId) throws Exception;

    /**
     * Удаляет предмет у преподавателя.
     *
     * @param teacherId идентификатор преподавателя.
     * @param subjectId идентификатор предмета.
     */
    SubjectJson deleteSubject(Long teacherId , Long subjectId) throws Exception;

    /**
     * Добавляет предмет преподавателю.
     *
     * @param teacherId идентификатор преподавателя.
     * @param subjectId идентификатор предмета.
     */
    SubjectJson addSubject(Long teacherId , Long subjectId) throws Exception;
}
