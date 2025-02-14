package com.learn.universityjpa.controller;

import com.learn.universityjpa.cache.component.GroupResponseComponent;
import com.learn.universityjpa.controller.model.json.SubjectJson;
import com.learn.universityjpa.controller.model.response.GroupResponse;
import com.learn.universityjpa.logging.CounterRequests;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class GroupControllerPublic
 * для работы с web сайтом /public/groups
 * http://localhost:8082/public/groups
 */
@RestController
@Validated
@Tag(name = "API работы с группами")
@RequestMapping("/public/groups")
public class GroupControllerPublic {
    @Autowired
    GroupResponseComponent groupResponseComponent;

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
}



