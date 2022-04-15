package com.learn.universityjpa.service.json;

import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Student;
import com.learn.universityjpa.entity.Subject;
import com.learn.universityjpa.entity.Teacher;
import com.learn.universityjpa.repo.GroupComponent;
import com.learn.universityjpa.repo.StudentComponent;
import com.learn.universityjpa.repo.SubjectComponent;
import com.learn.universityjpa.repo.TeacherComponent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class WriteDataToJsonImpl
 */
@Component
@PropertySource("classpath:values.properties")
public class WriteDataToJsonImpl implements WriteDataToJson {
    @Autowired
    private GroupComponent groupComponent;
    @Autowired
    SubjectComponent subjectComponent;
    @Autowired
    private StudentComponent studentComponent;
    @Autowired
    private TeacherComponent teacherComponent;

    @Value("${spring.dir.json}")
    private String filePath;
    @Override
    public void create() throws IOException {
        createJsonFileGroups();
        createJsonFileStudents();
        createJsonFileSubjects();
        createJsonFileTeachers();
    }

    private void createJsonFileTeachers() {
        JSONArray teacherList = new JSONArray();
        List<Teacher> teachers = teacherComponent.findAll();
        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = teachers.get(i);
            JSONObject teacherDetails = new JSONObject();
            teacherDetails.put("id", teacher.getId());
            teacherDetails.put("firstName", teacher.getFirstName());
            teacherDetails.put("secondName", teacher.getSecondName());
            teacherDetails.put("lastName", teacher.getLastName());
            teacherDetails.put("dateBirth", teacher.getDateBirth().toString());
            teacherDetails.put("gender", teacher.getGender().toString());
            teacherDetails.put("category", teacher.getCategory());
            teacherList.add(teacherDetails);
        }
        writeJsonFile(teacherList, "teachers.json");
    }

    private void createJsonFileSubjects() {
        JSONArray subjectList = new JSONArray();
        List<Subject> subjects = subjectComponent.findAll();
        for (int i = 0; i < subjects.size(); i++) {
            Subject subject = subjects.get(i);
            JSONObject subjectDetails = new JSONObject();
            subjectDetails.put("id", subject.getId());
            subjectDetails.put("name", subject.getName());
            subjectDetails.put("description", subject.getDescription());
            subjectList.add(subjectDetails);
        }
        writeJsonFile(subjectList, "subjects.json");
    }
    private void createJsonFileStudents() {
        JSONArray studentList = new JSONArray();
        List<Student> students = studentComponent.findAll();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            JSONObject groupDetails = new JSONObject();
            groupDetails.put("id",  student.getId());
            groupDetails.put("firstName", student.getFirstName());
            groupDetails.put("secondName", student.getSecondName());
            groupDetails.put("lastName", student.getLastName());
            groupDetails.put("dateBirth", student.getDateBirth().toString());
            groupDetails.put("gender", student.getGender().toString());
            groupDetails.put("group", student.getGroup().getName());
            studentList.add(groupDetails);
        }
        writeJsonFile(studentList, "students.json");
    }

    private void createJsonFileGroups() {
        JSONArray groupList = new JSONArray();
        List<Group> groups = groupComponent.findAll();
        for (int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);
            JSONObject groupDetails = new JSONObject();
            groupDetails.put("id", group.getId());
            groupDetails.put("name", group.getName());
            groupDetails.put("specification", group.getSpecification());
            groupList.add(groupDetails);
        }
        writeJsonFile(groupList, "groups.json");
    }
    private void writeJsonFile(JSONArray list, String fileName) {
        //Write JSON file
        try (FileWriter file = new FileWriter(new File("").getAbsolutePath() + filePath + fileName)) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(list.toJSONString());
            file.flush();

        } catch (IOException e) {

        }
    }
}


