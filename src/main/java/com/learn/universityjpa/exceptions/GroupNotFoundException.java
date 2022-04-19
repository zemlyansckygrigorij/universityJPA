package com.learn.universityjpa.exceptions;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class GroupNotFoundException для работы с ошибками поиска группы
 */
public class GroupNotFoundException extends BaseException {
    public GroupNotFoundException() {
        super(ErrorInformation.GROUP_NOT_FOUND.getDescription());
    }
}
