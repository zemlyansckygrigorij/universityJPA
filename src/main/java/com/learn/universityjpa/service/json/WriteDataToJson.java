package com.learn.universityjpa.service.json;

import java.io.IOException;

/**
 * Компонент для записи данных в json файлы.
 */
public interface WriteDataToJson {

    /**
     * Создает файл с данными.
     */
    void create() throws IOException;
}
