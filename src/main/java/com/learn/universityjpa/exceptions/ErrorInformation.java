package com.learn.universityjpa.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * enum ErrorInformation для видов ошибок
 * */
@RequiredArgsConstructor
@Getter
public enum ErrorInformation {
   GROUP_NOT_FOUND("Данная группа не найдена. Проверьте параметры поиска."),
   PERSON_NOT_FOUND("Данный человек не найден. Проверьте параметры поиска."),
   SUBJECT_NOT_FOUND("Данный предмет не найден. Проверьте параметры поиска.");
   /** Краткое описание ошибки. */
    private final String description;
}
