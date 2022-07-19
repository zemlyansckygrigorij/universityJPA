package com.learn.universityjpa.controller;

import com.learn.universityjpa.controller.model.request.GroupRequest;
import com.learn.universityjpa.controller.model.response.GroupResponse;
import com.learn.universityjpa.controller.model.response.StudentResponse;
import com.learn.universityjpa.controller.model.response.SubjectResponse;
import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.repo.GroupComponent;
import com.learn.universityjpa.repo.StudentComponent;
import com.learn.universityjpa.repo.SubjectComponent;
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
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class GroupController
 * для работы с web сайтом /groups
 */

@RestController
@Validated
@Tag(name = "API работы с группами")
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupComponent groupComponent;
    private final StudentComponent studentComponent;
    private final SubjectComponent subjectComponent;

    @GetMapping()
    public List<GroupResponse> getAllGroups() {
        return groupComponent.findAll().stream().map(
                (x) -> new GroupResponse(x)
        ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public GroupResponse getGroupById(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return new GroupResponse(groupComponent.findByIdOrDie(id));
    }

    @GetMapping("/{id}/subjects")
    public List<SubjectResponse> getSubjectsByGroupId(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return groupComponent
                .findByIdOrDie(id)
                .getSubjects()
                .stream()
                .map((x)-> new SubjectResponse(x))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/checksubject")
    public boolean checkSubject(@RequestBody final String name,
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return groupComponent
                .findByIdOrDie(id)
                .getSubjects()
                .stream()
                .filter((x)->x.getName().equals(name))
                .count() > 0;
    }

    @GetMapping("/{id}/students")
    public List<StudentResponse> getStudentsByGroupId(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return studentComponent.findAllByGroupId(id).stream().map(
                (x)-> new StudentResponse(x)
        ).collect(Collectors.toList());
    }
    @PostMapping()
    public GroupResponse createGroup(@RequestBody GroupRequest request) {
        return  new GroupResponse(groupComponent.commit(builder(request)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        groupComponent.deleteGroupById(id);
    }

    @PutMapping("/{id}")
    public void updateGroup(@RequestBody GroupRequest request,
            @PathVariable(name = "id") final long id
    ) throws Exception {
        groupComponent.updateGroupById(id, builder(request));
    }

    @PutMapping("/{id}/addSubject/{idSubject}")
    public void addSubject(
            @PathVariable(name = "id") final long id,
            @PathVariable(name = "idSubject") final long idSubject
    ) throws Exception {
        groupComponent.addSubject(groupComponent.findByIdOrDie(id), subjectComponent.findByIdOrDie(idSubject));
    }
    @PutMapping("/{id}/deleteSubject/{idSubject}")
    public void deleteSubject(
            @PathVariable(name = "id") final long id,
            @PathVariable(name = "idSubject") final long idSubject
    ) throws Exception {
        groupComponent.deleteSubject(groupComponent.findByIdOrDie(id), subjectComponent.findByIdOrDie(idSubject));
    }
    public Group builder(GroupRequest request) {
        Group group = new Group();
        if (Optional.ofNullable(request.getId()).isPresent()) {
            group.setId(request.getId());
        }
        group.setName(request.getName());
        group.setSpecification(request.getSpecification());
        return group;
    }
}


