package com.learn.universityjpa.exceptions;

import lombok.Getter;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class BaseException для общих свойств классов Exception-s
 */
@Getter
public abstract class BaseException extends RuntimeException {
    /** Краткое описание ошибки. */
    private final String description;
    public BaseException(final String description) {
        super(description);
        this.description = description;
    }
}
