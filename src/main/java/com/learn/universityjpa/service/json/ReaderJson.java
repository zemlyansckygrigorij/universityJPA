package com.learn.universityjpa.service.json;

import org.json.simple.JSONArray;

/**
 * Компонент для чтения json файлов .
 */
public interface ReaderJson {
    /**
     * читает файл с данными.
     */
    JSONArray readFileJson(String path) throws Exception;
}
