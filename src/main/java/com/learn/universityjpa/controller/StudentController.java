package com.learn.universityjpa.controller;

import com.learn.universityjpa.controller.model.request.StudentRequest;
import com.learn.universityjpa.controller.model.response.GroupResponse;
import com.learn.universityjpa.controller.model.response.StudentResponse;
import com.learn.universityjpa.controller.model.response.SubjectResponse;
import com.learn.universityjpa.entity.Gender;
import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Student;
import com.learn.universityjpa.repo.GroupComponent;
import com.learn.universityjpa.repo.StudentComponent;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
import java.util.stream.Collectors;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class StudentController
 * для работы с web сайтом /students
 */
@RestController
@Validated
@Tag(name = "API работы со студентами")
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentComponent studentComponent;
    private final GroupComponent groupComponent;

    @GetMapping()
    public List<StudentResponse>  getAllStudents() {
        return studentComponent
                .findAll()
                .stream()
                .map(StudentResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return new StudentResponse(studentComponent.findByIdOrDie(id));
    }

    @GetMapping("/group/{id}")
    public List<StudentResponse> getStudentByGroupId(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return studentComponent
                .findAllByGroupId(id)
                .stream()
                .map(StudentResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/group")
    public GroupResponse findGroup(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return new GroupResponse(studentComponent.findByIdOrDie(id).getGroup());
    }

    @GetMapping("/{id}/subjects")
    public List<SubjectResponse> findAllSubjects(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return studentComponent
                .findByIdOrDie(id)
                .getGroup()
                .getSubjects()
                .stream()
                .map(SubjectResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/name/{name}")
    public  List<StudentResponse> findStudentsByName(
            @PathVariable(name = "name") final String name
    ) throws Exception {
        return studentComponent
                .getStudentsByName(name)
                .stream()
                .map(StudentResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/check_subject")
    public boolean checkSubject(@RequestBody final String name,
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return studentComponent
                .findByIdOrDie(id)
                .getGroup()
                .getSubjects()
                .stream()
                .anyMatch((x)->x.getName().equals(name));
    }

    @PostMapping()
    public StudentResponse createStudent(@RequestBody StudentRequest request) throws Exception {
        Student student = studentBuilder(request);
        return new StudentResponse(studentComponent.commit(student));
    }

    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        studentComponent.deleteStudentById(id);
    }

    @PutMapping("/{id}")
    public void updateStudent(
            @RequestBody StudentRequest request,
            @PathVariable(name = "id") final long id
    ) throws Exception {
        studentComponent.updateStudentById(id, studentBuilder(request));
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







