package com.learn.universityjpa.cache.component;

import com.learn.universityjpa.controller.model.json.StudentJson;
import com.learn.universityjpa.controller.model.json.SubjectJson;
import com.learn.universityjpa.controller.model.response.GroupResponse;
import com.learn.universityjpa.db.entity.Group;
import com.learn.universityjpa.db.entity.Subject;
import java.util.List;
import java.util.Optional;

/**
 * Компонент работы с данными группы.
 */
public interface GroupResponseComponent {
    /**
     * Ищет группу по идентификатору.
     * @param id идентификатор группы.
     * @return группу.
     */
    Optional<GroupResponse> findById(Long id);

    /**
     * Ищет группу по идентификатору и падает по ошибке, если не нашел.
     *
     * @param id идентификатор группы.
     * @return группу.
     */
    GroupResponse findByIdOrDie(Long id) throws Exception;

    /**
     * Сохраняет группу.
     *
     * @param group группа для сохранения.
     * @return сохраненная группа.
     */
    GroupResponse commit(Group group);

    /**
     * Находит всех группы.
     *
     * @return список групп
     */
    List<GroupResponse> findAll();

    /**
     * Находит все предметы данной группы.
     *
     * @return список предметов
     */
    List<SubjectJson> findAllSubjectsByGroupId(Long id) throws Exception;

    /**
     * Проверяет наличие предмета в данной группе.
     *
     * @param  subjectName имя предмета.
     * @return наличие предмета в группе
     */
    boolean checkSubjectByGroupId(Long id, String subjectName) throws Exception;

    /**
     * Добавляет предмет в данную группу.
     * @param groupId идентификатор группы.
     * @param subjectId идентификатор предмета.
     */
    SubjectJson addSubject(Long groupId, Long subjectId) throws Exception;

    /**
     * Удаляет предмет из данной группы.
     *
     * @param groupId идентификатор группы.
     * @param subjectId идентификатор предмета.
     */
    SubjectJson deleteSubject(Long groupId, Long subjectId) throws Exception;
    /**
     * Находит всех студентов данной группы.
     *
     * @return список студентов
     */
    List<StudentJson> findAllStudentsByGroupId(Long id) throws Exception;


    /**
     * Найти группу по имени и падает по ошибке, если не нашел.
     *
     * @param name имя группы
     * @return список групп
     * @throws Exception может выбросить исключение
     */
    List<GroupResponse> findByName(String name) throws Exception;

    /**
     *  Найти группу по предметам и падает по ошибке, если не нашел.
     *
     * @param subjects список предметов
     * @return список групп
     * @throws Exception может выбросить исключение
     */
    List<GroupResponse> findBySubjects(List<Subject> subjects) throws Exception;

    /**
     * Удалить группу по идентификатору.
     *
     * @param id идентификатор группы.
     */
    void deleteGroupById(Long id) throws Exception;

    /**
     * Обновить группу по идентификатору.
     *
     * @param id идентификатор группы.
     * @param  group группа
     */
    void updateGroupById(Long id, Group group);
}
