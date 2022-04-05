package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Subject;

import java.util.List;
import java.util.Optional;

/**
 * Компонент работы с данными предметами .
 */
public interface SubjectComponent {

    /**
     * Ищет предмет по идентификатору.
     *
     * @param id идентификатор группы.
     * @return предмет.
     */
    Optional<Subject> findById(Long id);

    /**
     * Ищет предмет  по идентификатору и падает по ошибке, если не нашел.
     *
     * @param id идентификатор группы.
     * @return предмет.
     */
    Subject findByIdOrDie(Long id) throws Exception;

    /**
     * Сохраняет предмет.
     *
     * @param subject группа для сохранения.
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
     * Находит все предметы по введеному имени.
     *
     * @param nameSubject имя предмета.
     * @return список предметов.
     */
    List<Subject> getSubjectsByName(String nameSubject) throws Exception;
}
