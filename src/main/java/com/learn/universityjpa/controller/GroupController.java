package com.learn.universityjpa.controller;

import com.learn.universityjpa.cache.component.GroupResponseComponent;
import com.learn.universityjpa.controller.model.json.StudentJson;
import com.learn.universityjpa.controller.model.json.SubjectJson;
import com.learn.universityjpa.controller.model.request.GroupRequest;
import com.learn.universityjpa.controller.model.response.GroupResponse;
import com.learn.universityjpa.db.component.GroupComponent;
import com.learn.universityjpa.db.entity.Group;
import com.learn.universityjpa.logging.CounterRequests;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class GroupController
 * для работы с web сайтом /groups
 * http://localhost:8080/groups
 */
@RestController
@Validated
@Tag(name = "API работы с группами")
@RequestMapping("/groups")
public class GroupController {
    private final GroupComponent groupComponent;

    @Autowired
    GroupResponseComponent groupResponseComponent;

    @Autowired
    public GroupController(GroupComponent groupComponent) {
        this.groupComponent = groupComponent;
    }

    @CounterRequests
    @GetMapping()
    public List<GroupResponse> getAllGroups() {
        return groupResponseComponent.findAll();
    }

    @CounterRequests
    @GetMapping("/{id}")
    public GroupResponse getGroupById(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return groupResponseComponent.findByIdOrDie(id);
    }

    @CounterRequests
    @GetMapping("/{id}/subjects")
    public List<SubjectJson> getAllSubjectsByGroupId(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return groupResponseComponent.findAllSubjectsByGroupId(id);
    }

    @CounterRequests
    @GetMapping("/{id}/check_subject")
    public boolean checkSubject(
            @RequestBody final String name,
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return  groupResponseComponent.checkSubjectByGroupId(id, name);
    }

    @CounterRequests
    @GetMapping("/{id}/students")
    public List<StudentJson> findAllStudentsByGroupId(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return  groupResponseComponent.findAllStudentsByGroupId(id);
    }

    @CounterRequests
    @PostMapping()
    public GroupResponse createGroup(@RequestBody GroupRequest request) throws Exception {
        return groupResponseComponent.commit(builder(request));
    }

    @CounterRequests
    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        groupResponseComponent.deleteGroupById(id);
    }

    @CounterRequests
    @PutMapping("/{id}")
    public void updateGroup(@RequestBody GroupRequest request,
            @PathVariable(name = "id") final long id
    ) {
        groupResponseComponent.updateGroupById(id, builder(request));
    }

    @CounterRequests
    @PutMapping("/{id}/addSubject/{idSubject}")
    public void addSubject(
            @PathVariable(name = "id") final long id,
            @PathVariable(name = "idSubject") final long idSubject
    ) throws Exception {
        groupResponseComponent.addSubject(id,idSubject);
    }

    @CounterRequests
    @PutMapping("/{id}/deleteSubject/{idSubject}")
    public void deleteSubject(
            @PathVariable(name = "id") final long id,
            @PathVariable(name = "idSubject") final long idSubject
    ) throws Exception {
        groupResponseComponent.deleteSubject(id,idSubject);
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


