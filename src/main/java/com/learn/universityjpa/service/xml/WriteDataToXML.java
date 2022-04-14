package com.learn.universityjpa.service.xml;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Компонент для записи данных в xml файлы .
 */
public interface WriteDataToXML {
    /**
     * создает файл с данными.
     */
    void create() throws IOException, ParserConfigurationException, TransformerException;
}
