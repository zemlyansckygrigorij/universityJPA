package com.learn.universityjpa.controller;

import com.learn.universityjpa.cache.component.TeacherResponseComponent;
import com.learn.universityjpa.controller.model.json.SubjectJson;
import com.learn.universityjpa.controller.model.request.TeacherRequest;
import com.learn.universityjpa.controller.model.response.TeacherResponse;
import com.learn.universityjpa.db.entity.Gender;
import com.learn.universityjpa.db.entity.Teacher;
import com.learn.universityjpa.logging.CounterRequests;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
 * class TeacherController
 *  http://localhost:8080/teachers
 */

@RestController
@Validated
@Tag(name = "API работы со преподавателями")
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    @Autowired
    private TeacherResponseComponent component;


    @CounterRequests
    @GetMapping()
    public List<TeacherResponse> getAllTeachers() {
        return component.findAll();
    }

    @CounterRequests
    @GetMapping("/{id}")
    public TeacherResponse getTeacherById(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return component.findByIdOrDie(id);
    }

    @CounterRequests
    @GetMapping("/name/{name}")
    public List<TeacherResponse> findTeachersByName(@PathVariable(name = "name") final String name) throws Exception {
        return component.getTeachersByName(name);
    }

    @CounterRequests
    @PostMapping()
    public TeacherResponse createTeacher(@RequestBody TeacherRequest request) {
        return component.commit(teacherBuilder(request));
    }

    @CounterRequests
    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        component.deleteTeacherById(id);
    }

    @CounterRequests
    @PutMapping("/{id}")
    public void updateTeacher(@RequestBody TeacherRequest request,
                            @PathVariable(name = "id") final long id
    ) {
        component.updateTeacherById(id, teacherBuilder(request));
    }

    @CounterRequests
    @GetMapping("/{id}/subjects")
    public List<SubjectJson> findAllSubjects(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return component.findAllSubjects(id);
    }

    @CounterRequests
    @GetMapping("/{teacherId}/check_subject/{subjectid}")
    public boolean checkSubject(
                                @PathVariable(name = "teacherId") final long teacherId,
                                @PathVariable(name = "subjectid") final long subjectid
    ) throws Exception {
        return component.checkSubject(teacherId , subjectid);
    }

    @CounterRequests
    @PutMapping("/{teacherId}/addSubject/{subjectid}")
    public void addSubject(
            @PathVariable(name = "teacherId") final long teacherId,
            @PathVariable(name = "subjectid") final long subjectid
    ) throws Exception {
        component.addSubject(teacherId , subjectid);
    }

    @CounterRequests
    @PutMapping("/{teacherId}/deleteSubject/{subjectid}")
    @Transactional
    public void deleteSubject(
            @PathVariable(name = "teacherId") final long teacherId,
            @PathVariable(name = "subjectid") final long subjectid
    ) throws Exception {
        component.deleteSubject(teacherId , subjectid);
    }

    public Teacher teacherBuilder(TeacherRequest request) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(request.getFirstName());
        teacher.setSecondName(request.getSecondName());
        teacher.setLastName(request.getLastName());

        if (request.getGender().equals("FEMALE")) {
            teacher.setGender(Gender.FEMALE);
        }

        if (request.getGender().equals("MALE")) {
            teacher.setGender(Gender.MALE);
        }

        teacher.setDateBirth(request.getDateBirth());
        teacher.setCategory(request.getCategory());
        return teacher;
    }
}



