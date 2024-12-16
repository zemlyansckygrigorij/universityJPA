package com.learn.universityjpa.service.zip;

import org.springframework.stereotype.Component;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class WriterFilesToZipImpl
 */
@Component
public class WriterFilesToZipImpl implements WriterFilesToZip {
    @Override
    public void create(List<String> srcFiles, String filepath) throws IOException {


        try(FileOutputStream fos = new FileOutputStream(filepath);
            ZipOutputStream zos = new ZipOutputStream(fos)) {
            // create byte buffer
            byte[] buffer = new byte[1024];

            srcFiles.forEach(filePath->{
                File srcFile = new File(filePath);
                try(FileInputStream fis= new FileInputStream(srcFile)) {
                    zos.putNextEntry(new ZipEntry(srcFile.getName()));
                    int length;

                    while (true) {
                        if (!((length = fis.read(buffer)) > 0)) break;
                        zos.write(buffer, 0, length);
                    }
                    zos.closeEntry();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
