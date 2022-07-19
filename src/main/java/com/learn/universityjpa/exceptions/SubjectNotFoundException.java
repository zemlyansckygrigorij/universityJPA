package com.learn.universityjpa.exceptions;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class PersonNotFoundException для работы с ошибками поиска предмета
 */
public class SubjectNotFoundException  extends BaseException  {
    public SubjectNotFoundException() {
        super(ErrorInformation.SUBJECT_NOT_FOUND.getDescription());
    }
}
