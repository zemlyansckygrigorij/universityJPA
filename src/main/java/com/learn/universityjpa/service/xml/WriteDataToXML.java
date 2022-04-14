package com.learn.universityjpa.service.xml;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * Компонент для записи данных в xml файлы .
 */
public interface WriteDataToXML {
    /**
     * создает файл с данными.
     */
    void create() throws IOException, ParserConfigurationException, TransformerException;
}
