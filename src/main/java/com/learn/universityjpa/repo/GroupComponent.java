package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Subject;

import java.util.List;
import java.util.Optional;

/**
 * Компонент работы с данными группы.
 */
public interface GroupComponent {

    /**
     * Ищет группу по идентификатору.
     *
     * @param id идентификатор группы.
     * @return группу.
     */
    Optional<Group> findById(Long id);

    /**
     * Ищет группу по идентификатору и падает по ошибке, если не нашел.
     *
     * @param id идентификатор группы.
     * @return группу.
     */
    Group findByIdOrDie(Long id) throws Exception;

    /**
     * Сохраняет группу.
     *
     * @param group группа для сохранения.
     * @return сохраненная группа.
     */
    Group commit(Group group);

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
     * @param  subject предмет.
     * @return наличие предмета в группе
     */
    boolean checkSubject(Group group, Subject subject);

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
     * Найти группу по имени и падает по ошибке, если не нашел.
     *
     * @param name
     * @return список групп
     * @throws Exception
     */
    List<Group> findByName(String name) throws Exception;

    /**
     *  Найти группу по предметам и падает по ошибке, если не нашел.
     *
     * @param subjects список предметов
     * @return список групп
     * @throws Exception
     */
    List<Group> findBySubjects(List<Subject> subjects)throws Exception;

    /**
     * Удалить группу по по идентификатору.
     *
     * @param id идентификатор группы.
     */
    void deleteGroupById(Long id) throws Exception;

    /**
     * Обновить группу по по идентификатору.
     *
     * @param id идентификатор группы.
     * @param  group группа .
     */
    void updateGroupById(Long id, Group group);
}
