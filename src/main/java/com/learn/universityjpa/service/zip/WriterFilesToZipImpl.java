package com.learn.universityjpa.service.zip;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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
        String zipFile = filepath;

        try {

            // create byte buffer
            byte[] buffer = new byte[1024];

            FileOutputStream fos = new FileOutputStream(zipFile);

            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int i = 0; i < srcFiles.size(); i++) {

                File srcFile = new File(srcFiles.get(i));

                FileInputStream fis = new FileInputStream(srcFile);

                // begin writing a new ZIP entry, positions the stream to the start of the entry data
                zos.putNextEntry(new ZipEntry(srcFile.getName()));

                int length;

                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }

                zos.closeEntry();

                // close the InputStream
                fis.close();

            }

            // close the ZipOutputStream
            zos.close();
        } catch (IOException ioe) {
        }
    }
}
