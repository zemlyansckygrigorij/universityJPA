package com.learn.universityjpa.service.xml;

import com.learn.universityjpa.annotations.SqlTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
@PropertySource("classpath:values.properties")
class WriteDataToXMLImplTest {

    @Value("${spring.dir.xml}")
    private String filePath;

    @Autowired
    private WriteDataToXML writeDataToXML;

    @DisplayName("Проверка создания xml файла")
    @SqlTest
    void create() throws IOException, ParserConfigurationException, TransformerException {
        String path = new File("").getAbsolutePath() + filePath;
        assertNotNull(writeDataToXML);
        assertNotNull(filePath);
        Files.deleteIfExists(new File(path + "groups.xml").toPath());
        Files.deleteIfExists(new File(path + "students.xml").toPath());
        Files.deleteIfExists(new File(path + "subjects.xml").toPath());
        Files.deleteIfExists(new File(path + "teachers.xml").toPath());
        writeDataToXML.create();
        assertTrue(new File(path + "groups.xml").exists());
        assertTrue(new File(path + "students.xml").exists());
        assertTrue(new File(path + "subjects.xml").exists());
        assertTrue(new File(path + "teachers.xml").exists());
// Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(path + "groups.xml"));

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            assertEquals("groups", doc.getDocumentElement().getNodeName());

            NodeList list = doc.getElementsByTagName("group");
            assertEquals(2, list.getLength());
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String id = element.getAttribute("id");

                    // get text
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String specification = element.getElementsByTagName("specification").item(0).getTextContent();
                }
            }

            doc = db.parse(new File(path + "students.xml"));
            doc.getDocumentElement().normalize();
            assertEquals("students", doc.getDocumentElement().getNodeName());
            list = doc.getElementsByTagName("student");
            assertEquals(72, list.getLength());

            doc = db.parse(new File(path + "subjects.xml"));
            doc.getDocumentElement().normalize();
            assertEquals("subjects", doc.getDocumentElement().getNodeName());
            list = doc.getElementsByTagName("subject");
            assertEquals(7, list.getLength());

            doc = db.parse(new File(path + "teachers.xml"));
            doc.getDocumentElement().normalize();
            assertEquals("teachers", doc.getDocumentElement().getNodeName());
            list = doc.getElementsByTagName("teacher");
            assertEquals(9, list.getLength());

        } catch (ParserConfigurationException | SAXException | IOException e) {
        }
    }
}
