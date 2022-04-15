package com.learn.universityjpa.service.zip;

import java.io.IOException;
import java.util.List;

/**
 * Компонент для создания zip архива .
 */
public interface WriterFilesToZip {

   /**
    * создает zip архив.
    */
   void create(List<String> srcFiles, String filepath) throws IOException;
}
