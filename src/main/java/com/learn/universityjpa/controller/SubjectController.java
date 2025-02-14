package com.learn.universityjpa.controller;

import com.learn.universityjpa.cache.component.SubjectResponseComponent;
import com.learn.universityjpa.controller.model.request.SubjectRequest;
import com.learn.universityjpa.controller.model.response.SubjectResponse;
import com.learn.universityjpa.db.component.GroupComponent;
import com.learn.universityjpa.db.entity.Subject;
import com.learn.universityjpa.logging.CounterRequests;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class SubjectController
 * для работы с web сайтом /subjects
 *
 * localhost:8082/subjects
 */
@RestController
@Validated
@Tag(name = "API работы с предметами")
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {
    @Autowired
    private GroupComponent groupComponent;

    @Autowired
    SubjectResponseComponent component;

    @PreAuthorize("hasRole('USER')")
    @CounterRequests
    @GetMapping()
    public List<SubjectResponse> getAllSubjects() {
        return component.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @CounterRequests
    @GetMapping("/name/{name}")
    public List<SubjectResponse> findSubjectsByName(
            @PathVariable(name = "name") final String name) throws Exception {
        return component.getSubjectsByName(name);
    }

    @PreAuthorize("hasRole('USER')")
    @CounterRequests
    @GetMapping("/{id}")
    public SubjectResponse getSubjectById(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return component.findByIdOrDie(id);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @CounterRequests
    @PostMapping()
    public SubjectResponse createSubject(@RequestBody SubjectRequest request) {
        return component.commit(subjectBuilder(request));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @CounterRequests
    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        component.deleteSubjectById(id);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @CounterRequests
    @PutMapping("/{id}")
    public void updateSubject(@RequestBody SubjectRequest request,
                              @PathVariable(name = "id") final long id
    ) throws Exception {
       component.updateSubjectById(id, subjectBuilder(request));
    }

    public Subject subjectBuilder(SubjectRequest request) {
        Subject subject = new Subject();
        subject.setName(request.getName());
        subject.setDescription(request.getDescription());

        if (Optional.ofNullable(request.getGroups()).isPresent()) {
            subject.setGroups(
                    request
                            .getGroups()
                            .stream()
                            .map(
                                    (x) -> {
                                        try {
                                            return groupComponent.findByIdOrDie(x);
                                        } catch (Exception e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                            )
                            .collect(Collectors.toList()));
        }
        return subject;
    }
}


