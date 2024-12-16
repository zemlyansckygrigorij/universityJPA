package com.learn.universityjpa.service.json;

import org.json.simple.JSONArray;

/**
 * Компонент для чтения json файлов.
 */
public interface ReaderJson {
    /**
     * Читает файл с данными.
     */
    JSONArray readFileJson(String path) throws Exception;
}
