package com.learn.universityjpa.cache.component;

import com.learn.universityjpa.controller.model.response.SubjectResponse;
import com.learn.universityjpa.db.entity.Subject;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * Компонент работы с данными предметов.
 */
public interface SubjectResponseComponent { /**
 * Ищет предмет по идентификатору.
 *
 * @param id идентификатор предмета.
 * @return предмет.
 */
Optional<SubjectResponse> findById(Long id);

    /**
     * Ищет предмет по идентификатору и падает по ошибке, если не нашел.
     *
     * @param id идентификатор предмета.
     * @return предмет.
     */
    SubjectResponse findByIdOrDie(Long id) throws Exception;

    /**
     * Сохраняет предмет.
     *
     * @param subject предмет для сохранения.
     * @return сохраненный предмет.
     */
    SubjectResponse commit(Subject subject);

    /**
     * Находит всех предметы.
     *
     * @return список предметов.
     */
    List<SubjectResponse> findAll();

    /**
     * Находит все предметы по имени.
     *
     * @param nameSubject имя предмета.
     * @return список предметов.
     */
    List<SubjectResponse> getSubjectsByName(String nameSubject) throws Exception;

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
