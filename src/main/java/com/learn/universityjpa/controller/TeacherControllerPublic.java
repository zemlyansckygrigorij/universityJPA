package com.learn.universityjpa.controller;

import com.learn.universityjpa.cache.component.TeacherResponseComponent;
import com.learn.universityjpa.controller.model.json.SubjectJson;
import com.learn.universityjpa.controller.model.response.TeacherResponse;
import com.learn.universityjpa.logging.CounterRequests;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class TeacherControllerPublic
 *  http://localhost:8082/public/teachers
 */

@RestController
@Validated
@Tag(name = "API работы со преподавателями")
@RequestMapping("/public/teachers")
@RequiredArgsConstructor
public class TeacherControllerPublic {
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
}


