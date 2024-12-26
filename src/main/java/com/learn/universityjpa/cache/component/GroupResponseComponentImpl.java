package com.learn.universityjpa.cache.component;

import com.learn.universityjpa.cache.repo.GroupResponseRepository;
import com.learn.universityjpa.controller.model.response.GroupResponse;
import com.learn.universityjpa.controller.model.response.SubjectResponse;
import com.learn.universityjpa.db.component.GroupComponent;
import com.learn.universityjpa.db.entity.Group;
import com.learn.universityjpa.db.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class GroupResponseComponentImpl implements GroupResponseComponent{
    @Autowired
    private GroupResponseRepository repo;

    @Autowired
    private GroupComponent studentComponent;

    @Override
    public Optional<GroupResponse> findById(Long id) {
        return repo.findById(String.valueOf(id));
    }

    @Override
    public GroupResponse findByIdOrDie(Long id) throws Exception {
        return findById(id).orElseThrow();
    }

    @Override
    public GroupResponse commit(Group group) {
        return null;
    }

    @Override
    public List<GroupResponse> findAll() {
        return List.of();
    }

    @Override
    public List<SubjectResponse> findAllSubjects(Group group) {
        return List.of();
    }

    @Override
    public boolean checkSubject(Group group, Subject subject) {
        return false;
    }

    @Override
    public SubjectResponse addSubject(Group group, Subject subject) {
        return null;
    }

    @Override
    public SubjectResponse deleteSubject(Group group, Subject subject) {
        return null;
    }

    @Override
    public List<GroupResponse> findByName(String name) throws Exception {
        return List.of();
    }

    @Override
    public List<GroupResponse> findBySubjects(List<Subject> subjects) throws Exception {
        return List.of();
    }

    @Override
    public void deleteGroupById(Long id) throws Exception {

    }

    @Override
    public void updateGroupById(Long id, Group group) {

    }
}
