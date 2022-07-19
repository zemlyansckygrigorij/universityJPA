package com.learn.universityjpa.controller;

import com.learn.universityjpa.controller.model.request.TeacherRequest;
import com.learn.universityjpa.controller.model.response.SubjectResponse;
import com.learn.universityjpa.controller.model.response.TeacherResponse;
import com.learn.universityjpa.entity.Gender;
import com.learn.universityjpa.entity.Teacher;
import com.learn.universityjpa.repo.SubjectComponent;
import com.learn.universityjpa.repo.TeacherComponent;
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

import java.util.stream.Collectors;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class TeacherController
 */

@RestController
@Validated
@Tag(name = "API работы со преподавателями")
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    @Autowired
    private TeacherComponent teacherComponent;

    @Autowired
    private SubjectComponent subjectComponent;

    @GetMapping()
    public List<TeacherResponse> getAllTeachers() {
        return teacherComponent
                .findAll()
                .stream()
                .map((x)->new TeacherResponse(x))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TeacherResponse getTeacherById(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return new TeacherResponse(teacherComponent.findByIdOrDie(id));
    }

    @GetMapping("/name/{name}")
    public List<TeacherResponse> findTeachersByName(@PathVariable(name = "name") final String name) throws Exception {
        return teacherComponent.getTeachersByName(name).stream().map(
                (x)-> new TeacherResponse(x)
        ).collect(Collectors.toList());
    }

    @PostMapping()
    public TeacherResponse createTeacher(@RequestBody TeacherRequest request) throws Exception {
        return new TeacherResponse(teacherComponent.commit(teacherBuilder(request)));
    }
    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        teacherComponent.deleteTeacherById(id);
    }
    @PutMapping("/{id}")
    public void updateTeacher(@RequestBody TeacherRequest request,
                            @PathVariable(name = "id") final long id
    ) throws Exception {
        teacherComponent.updateTeacherById(id, teacherBuilder(request));
    }

    @GetMapping("/{id}/subjects")
    public List<SubjectResponse> findAllSubjects(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return teacherComponent.findByIdOrDie(id).getSubjects().stream().map(
                (x)-> new SubjectResponse(x)
        ).collect(Collectors.toList());
    }

    @GetMapping("/{id}/checksubject")
    public boolean checkSubject(@RequestBody final String name,
                                @PathVariable(name = "id") final long id
    ) throws Exception {
        return teacherComponent
                .findByIdOrDie(id)
                .getSubjects()
                .stream()
                .filter((x)->x.getName().equals(name))
                .count() > 0;
    }

    @PutMapping("/{id}/addSubject/{idSubject}")
    public void addSubject(
            @PathVariable(name = "id") final long id,
            @PathVariable(name = "idSubject") final long idSubject
    ) throws Exception {
        teacherComponent.addSubject(id, idSubject);
    }

    @PutMapping("/{id}/deleteSubject/{idSubject}")
    @Transactional
    public void deleteSubject(
            @PathVariable(name = "id") final long id,
            @PathVariable(name = "idSubject") final long idSubject
    ) throws Exception {
       teacherComponent.deleteSubject(id, idSubject);
    }

    public Teacher teacherBuilder(TeacherRequest request) throws Exception {
        Teacher teacher = new Teacher();
        teacher.setFirstName(request.getFirstName());
        teacher.setSecondName(request.getSecondName());
        teacher.setLastName(request.getLastName());

        if (request.getGender().toString().equals("FEMALE")) {
            teacher.setGender(Gender.FEMALE);
        }
        if (request.getGender().toString().equals("MALE")) {
            teacher.setGender(Gender.MALE);
        }
        teacher.setDateBirth(request.getDateBirth());
        teacher.setCategory(request.getCategory());
        return teacher;
    }
}



