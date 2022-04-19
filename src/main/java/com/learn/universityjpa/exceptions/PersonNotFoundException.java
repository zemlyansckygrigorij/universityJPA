package com.learn.universityjpa.exceptions;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class PersonNotFoundException для работы с ошибками поиска человека
 */
public class PersonNotFoundException  extends BaseException {
    public PersonNotFoundException() {
        super(ErrorInformation.PERSON_NOT_FOUND.getDescription());
    }
}
