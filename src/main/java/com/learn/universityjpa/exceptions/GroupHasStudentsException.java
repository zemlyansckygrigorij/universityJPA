package com.learn.universityjpa.exceptions;
/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class GroupHasStudentsException для работы с ошибками удаления группы, на которую ссылаются студенты
 */
public class GroupHasStudentsException  extends BaseException {
    public GroupHasStudentsException() {
        super(ErrorInformation.GROUP_HAS_STUDENTS.getDescription());
    }
}
