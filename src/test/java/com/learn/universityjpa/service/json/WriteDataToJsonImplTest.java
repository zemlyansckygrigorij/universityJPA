package com.learn.universityjpa.service.json;

import com.learn.universityjpa.annotations.SqlTest;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
@PropertySource("classpath:values.properties")
class WriteDataToJsonImplTest {
    @Value("${spring.dir.json}")
    private String filePath;

    @Autowired
    private WriteDataToJson writeDataToJson;
    @Autowired
    private ReaderJsonImpl readerJsonImpl;
    @DisplayName("Проверка создания json файлов")
    @SqlTest
    @SuppressWarnings("unchecked")
    void create() throws Exception {
        String path = new File("").getAbsolutePath() + filePath;
        assertNotNull(writeDataToJson);
        assertNotNull(filePath);
        Files.deleteIfExists(new File(path + "groups.json").toPath());
        Files.deleteIfExists(new File(path + "students.json").toPath());
        Files.deleteIfExists(new File(path + "subjects.json").toPath());
        Files.deleteIfExists(new File(path + "teachers.json").toPath());
        writeDataToJson.create();
        assertTrue(new File(path + "groups.json").exists());
        assertTrue(new File(path + "students.json").exists());
        assertTrue(new File(path + "subjects.json").exists());
        assertTrue(new File(path + "teachers.json").exists());

        JSONArray groupsList = readerJsonImpl.readFileJson(path + "groups.json");
        assertEquals(2, groupsList.size());

        JSONArray studentsList = readerJsonImpl.readFileJson(path + "students.json");
        assertEquals(72, studentsList.size());

        JSONArray subjectsList = readerJsonImpl.readFileJson(path + "subjects.json");
        assertEquals(7, subjectsList.size());

        JSONArray teachersList = readerJsonImpl.readFileJson(path + "teachers.json");
        assertEquals(9, teachersList.size());

    }
}
