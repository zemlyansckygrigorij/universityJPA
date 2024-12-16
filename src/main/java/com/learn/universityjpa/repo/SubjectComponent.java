package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Subject;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * Компонент работы с данными предметов.
 */
public interface SubjectComponent {

    /**
     * Ищет предмет по идентификатору.
     *
     * @param id идентификатор предмета.
     * @return предмет.
     */
    Optional<Subject> findById(Long id);

    /**
     * Ищет предмет по идентификатору и падает по ошибке, если не нашел.
     *
     * @param id идентификатор предмета.
     * @return предмет.
     */
    Subject findByIdOrDie(Long id) throws Exception;

    /**
     * Сохраняет предмет.
     *
     * @param subject предмет для сохранения.
     * @return сохраненный предмет.
     */
    Subject commit(Subject subject);

    /**
     * Находит всех предметы.
     *
     * @return список предметов.
     */
    List<Subject> findAll();

    /**
     * Находит все предметы по имени.
     *
     * @param nameSubject имя предмета.
     * @return список предметов.
     */
    List<Subject> getSubjectsByName(String nameSubject) throws Exception;

    /**
     * Удалить предмета по идентификатору.
     *
     * @param id идентификатор предмета.
     */
    void deleteSubjectById(Long id);

    /**
     * Обновить предмет по идентификатору.
     *
     * @param id идентификатор предмета.
     * @param  subject предмет.
     */
    void updateSubjectById(Long id, Subject subject) throws ParseException;
}
