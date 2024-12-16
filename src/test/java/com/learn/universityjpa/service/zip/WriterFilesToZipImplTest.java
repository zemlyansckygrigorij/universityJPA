package com.learn.universityjpa.service.zip;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
@PropertySource("classpath:values.properties")
class WriterFilesToZipImplTest {
    @Value("${spring.dir.zip}")
    private String filePath;
    @Value("${spring.dir.json}")
    private String filePathJson;
    @Autowired
    private WriterFilesToZip writerFilesToZip;

    @Test
    void create() throws IOException {
        String pathFiles = new File("").getAbsolutePath() + filePathJson;
        File folder = new File(pathFiles);
        File[] listOfFiles = folder.listFiles();
        List<String> list = new ArrayList<>();
        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                list.add(file.getAbsolutePath());
            }
        }

        String pathZip = new File("").getAbsolutePath() + filePath + "data.zip";
        Files.deleteIfExists(new File(pathZip).toPath());
        writerFilesToZip.create(list, pathZip);
        assertTrue(new File(pathZip).exists());
    }
}
