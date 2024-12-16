package com.learn.universityjpa.service.excel;

import java.io.IOException;

/**
 * Компонент для записи данных в excel файлы.
 */
public interface WriteDataToExcel {

    /**
     * Создает файл с данными.
     */
   void create() throws IOException;
}
