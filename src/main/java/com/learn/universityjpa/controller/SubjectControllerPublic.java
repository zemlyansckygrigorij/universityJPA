package com.learn.universityjpa.controller;

import com.learn.universityjpa.cache.component.SubjectResponseComponent;
import com.learn.universityjpa.controller.model.response.SubjectResponse;
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
 * class SubjectControllerPublic
 * для работы с web сайтом /public/subjects
 *
 * localhost:8082/public/subjects
 */
@RestController
@Validated
@Tag(name = "API работы с предметами")
@RequestMapping("/public/subjects")
@RequiredArgsConstructor
public class SubjectControllerPublic {

    @Autowired
    SubjectResponseComponent component;

    @CounterRequests
    @GetMapping()
    public List<SubjectResponse> getAllSubjects() {
        return component.findAll();
    }

    @CounterRequests
    @GetMapping("/name/{name}")
    public List<SubjectResponse> findSubjectsByName(
            @PathVariable(name = "name") final String name) throws Exception {
        return component.getSubjectsByName(name);
    }

    @CounterRequests
    @GetMapping("/{id}")
    public SubjectResponse getSubjectById(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return component.findByIdOrDie(id);
    }
}



