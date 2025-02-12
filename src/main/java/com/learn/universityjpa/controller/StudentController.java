package com.learn.universityjpa.controller;

import com.learn.universityjpa.cache.component.StudentResponseComponent;
import com.learn.universityjpa.controller.model.request.StudentRequest;
import com.learn.universityjpa.controller.model.response.StudentResponse;
import com.learn.universityjpa.db.component.GroupComponent;
import com.learn.universityjpa.db.entity.Gender;
import com.learn.universityjpa.db.entity.Group;
import com.learn.universityjpa.db.entity.Student;
import com.learn.universityjpa.logging.CounterRequests;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class StudentController
 * для работы с web сайтом /students
 *  http://localhost:8080/students
 */
@RestController
@Validated
@Tag(name = "API работы со студентами")
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    StudentResponseComponent studentResponseComponent;
    @Autowired
    GroupComponent groupComponent;
    @CounterRequests
    @GetMapping()
    public List<StudentResponse>  getAllStudents() {
        return studentResponseComponent.findAll();
    }

    @CounterRequests
    @GetMapping("/{id}")
    public StudentResponse getStudentById(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return studentResponseComponent.findByIdOrDie(id);
    }

    @GetMapping("/{id}/group")
    public String findGroup(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return studentResponseComponent.findByIdOrDie(id).getGroupName();
    }

    @CounterRequests
    @GetMapping("/name/{name}")
    public  List<StudentResponse> findStudentsByName(
            @PathVariable(name = "name") final String name
    ) throws Exception {
        return studentResponseComponent.findStudentsByName(name);
    }

    @CounterRequests
    @PostMapping()
    public StudentResponse createStudent(@RequestBody StudentRequest request) throws Exception {
        return studentResponseComponent.commit(studentBuilder(request));
    }

    @CounterRequests
    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        studentResponseComponent.deleteStudentById(id);
    }

    @CounterRequests
    @PutMapping("/{id}")
    public void updateStudent(
            @RequestBody StudentRequest request,
            @PathVariable(name = "id") final long id
    ) throws Exception {
        studentResponseComponent.updateStudentById(id, studentBuilder(request));
    }

    public Student studentBuilder(StudentRequest request) throws Exception {
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setSecondName(request.getSecondName());
        student.setLastName(request.getLastName());

        if (request.getGender().equals("FEMALE")) {
            student.setGender(Gender.FEMALE);
        }

        if (request.getGender().equals("MALE")) {
            student.setGender(Gender.MALE);
        }

        student.setDateBirth(request.getDateBirth());

        Group group = groupComponent.findByIdOrDie(request.getGroupId());
        student.setGroup(group);
        return student;
    }
}







