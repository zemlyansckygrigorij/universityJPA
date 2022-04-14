package com.learn.universityjpa.service.xml;

import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Student;
import com.learn.universityjpa.entity.Subject;
import com.learn.universityjpa.entity.Teacher;

import com.learn.universityjpa.repo.GroupComponent;
import com.learn.universityjpa.repo.StudentComponent;
import com.learn.universityjpa.repo.SubjectComponent;
import com.learn.universityjpa.repo.TeacherComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class WriteDataToXMLImpl
 */
@Component
@PropertySource("classpath:values.properties")
public class WriteDataToXMLImpl implements WriteDataToXML {
    @Autowired
    private GroupComponent groupComponent;
    @Autowired
    SubjectComponent subjectComponent;
    @Autowired
    private StudentComponent studentComponent;
    @Autowired
    private TeacherComponent teacherComponent;

    @Value("${spring.dir.xml}")
    private String filePath;

    @Override
    public void create() {
        try {
            createXMLFile(Files.Groups);
            createXMLFile(Files.Subjects);
            createXMLFile(Files.Students);
            createXMLFile(Files.Teacher);
        } catch (ParserConfigurationException | TransformerException ex) { }
    }

    private void createXMLFile(Files file) throws ParserConfigurationException, TransformerException {

        String fileName;
        Document document = createDocument();
        if (file == Files.Groups) {
            createGroupsDocument(document);
            fileName = "groups.xml";
        } else if (file == Files.Subjects) {
            createSubjectsDocument(document);
            fileName = "subjects.xml";
        } else if (file == Files.Students) {
            createStudentsDocument(document);
            fileName = "students.xml";
        } else if (file == Files.Teacher) {
            createTeachersDocument(document);
            fileName = "teachers.xml";
        } else {
            return;
        }

        // create the xml file
        //transform the DOM Object to an XML File
        Transformer transformer = getTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = getStreamResult(new File("").getAbsolutePath() + filePath + fileName);

        // If you use
        // StreamResult result = new StreamResult(System.out);
        // the output will be pushed to the standard output ...
        // You can use that for debugging
        transformer.transform(domSource, streamResult);
    }

    private void createTeachersDocument(Document document) {
        List<Teacher> teachers = teacherComponent.findAll();
        Element root = document.createElement("teachers");
        document.appendChild(root);
        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = teachers.get(i);

            Element studentEl = document.createElement("teacher");
            root.appendChild(studentEl);

            Element id = document.createElement("id");
            id.appendChild(document.createTextNode(teacher.getId().toString()));
            studentEl.appendChild(id);

            Element firstName = document.createElement("firstName");
            firstName.appendChild(document.createTextNode(teacher.getFirstName()));
            studentEl.appendChild(firstName);

            Element secondName = document.createElement("secondName");
            secondName.appendChild(document.createTextNode(teacher.getSecondName()));
            studentEl.appendChild(secondName);

            Element lastName = document.createElement("lastName");
            lastName.appendChild(document.createTextNode(teacher.getLastName()));
            studentEl.appendChild(lastName);

            Element name = document.createElement("firstName");
            name.appendChild(document.createTextNode(teacher.getFirstName()));
            studentEl.appendChild(name);

            Element dateBirth = document.createElement("dateBirth");
            dateBirth.appendChild(document.createTextNode(teacher.getDateBirth().toString()));
            studentEl.appendChild(dateBirth);

            Element category = document.createElement("category");
            category.appendChild(document.createTextNode(teacher.getCategory()));
            studentEl.appendChild(category);

            Element gender = document.createElement("gender");
            gender.appendChild(document.createTextNode(teacher.getGender().toString()));
            studentEl.appendChild(gender);

            Attr attr0 = document.createAttribute("id");
            attr0.setValue(teacher.getId().toString());
            studentEl.setAttributeNode(attr0);
        }
    }

    private void createStudentsDocument(Document document) {
        List<Student> students = studentComponent.findAll();
        Element root = document.createElement("students");
        document.appendChild(root);

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);

            Element studentEl = document.createElement("student");
            root.appendChild(studentEl);

            Element id = document.createElement("id");
            id.appendChild(document.createTextNode(student.getId().toString()));
            studentEl.appendChild(id);

            Element firstName = document.createElement("firstName");
            firstName.appendChild(document.createTextNode(student.getFirstName()));
            studentEl.appendChild(firstName);

            Element secondName = document.createElement("secondName");
            secondName.appendChild(document.createTextNode(student.getSecondName()));
            studentEl.appendChild(secondName);

            Element lastName = document.createElement("lastName");
            lastName.appendChild(document.createTextNode(student.getLastName()));
            studentEl.appendChild(lastName);

            Element name = document.createElement("firstName");
            name.appendChild(document.createTextNode(student.getFirstName()));
            studentEl.appendChild(name);

            Element dateBirth = document.createElement("dateBirth");
            dateBirth.appendChild(document.createTextNode(student.getDateBirth().toString()));
            studentEl.appendChild(dateBirth);

            Element group = document.createElement("group");
            group.appendChild(document.createTextNode(student.getGroup().getName()));
            studentEl.appendChild(group);

            Element gender = document.createElement("gender");
            gender.appendChild(document.createTextNode(student.getGender().toString()));
            studentEl.appendChild(gender);

            Attr attr0 = document.createAttribute("id");
            attr0.setValue(student.getId().toString());
            studentEl.setAttributeNode(attr0);
        }
    }


    private void createSubjectsDocument(Document document) {
        List<Subject> subjects = subjectComponent.findAll();
        Element root = document.createElement("subjects");
        document.appendChild(root);

        for (int i = 0; i < subjects.size(); i++) {

            Subject subject = subjects.get(i);

            Element subjectEl = document.createElement("subject");
            root.appendChild(subjectEl);

            Element id = document.createElement("id");
            id.appendChild(document.createTextNode(subject.getId().toString()));
            subjectEl.appendChild(id);

            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(subject.getName()));
            subjectEl.appendChild(name);

            Element specification = document.createElement("description");
            specification.appendChild(document.createTextNode(subject.getDescription()));
            subjectEl.appendChild(specification);

            Attr attr0 = document.createAttribute("id");
            attr0.setValue(subject.getId().toString());
            subjectEl.setAttributeNode(attr0);

            Attr attr1 = document.createAttribute("Name");
            attr1.setValue(subject.getName());
            subjectEl.setAttributeNode(attr1);
        }
    }

    private void createGroupsDocument(Document document) {
        List<Group> groups = groupComponent.findAll();
        // root element
        Element root = document.createElement("groups");
        document.appendChild(root);

        for (int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);
            Element groupEl = document.createElement("group");
            root.appendChild(groupEl);

            Element id = document.createElement("id");
            id.appendChild(document.createTextNode(group.getId().toString()));
            groupEl.appendChild(id);

            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(group.getName()));
            groupEl.appendChild(name);

            Element specification = document.createElement("specification");
            specification.appendChild(document.createTextNode(group.getSpecification()));
            groupEl.appendChild(specification);

            Attr attr0 = document.createAttribute("id");
            attr0.setValue(group.getId().toString());
            groupEl.setAttributeNode(attr0);

            Attr attr1 = document.createAttribute("Name");
            attr1.setValue(group.getName());
            groupEl.setAttributeNode(attr1);
        }
    }



    private StreamResult getStreamResult(String path) {
        return new StreamResult(new File(path));
    }

    private Transformer getTransformer() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        return transformerFactory.newTransformer();
    }

    private Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        return documentBuilder.newDocument();
    }
}
