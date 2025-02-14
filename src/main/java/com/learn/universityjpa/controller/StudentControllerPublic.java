package com.learn.universityjpa.controller;

import com.learn.universityjpa.cache.component.StudentResponseComponent;
import com.learn.universityjpa.controller.model.response.StudentResponse;
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
 * class StudentControllerPublic
 * для работы с web сайтом /public/students
 *  http://localhost:8082/public/students
 */
@RestController
@Validated
@Tag(name = "API работы со студентами")
@RequestMapping("/public/students")
@RequiredArgsConstructor
public class StudentControllerPublic {
    @Autowired
    StudentResponseComponent studentResponseComponent;

    @CounterRequests
    @GetMapping()
    public List<StudentResponse> getAllStudents() {
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
}

