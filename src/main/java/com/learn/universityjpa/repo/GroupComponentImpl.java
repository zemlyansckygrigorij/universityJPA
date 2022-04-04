package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class GroupComponentImpl
 */
@RequiredArgsConstructor
@Component
public class GroupComponentImpl implements GroupComponent {
    private final GroupRepository repo;

    @Override
    public Optional<Group> findById(Long id) {
        return this.repo.findById(id);
    }

    @Override
    public Group findByIdOrDie(Long id) throws Exception {
        return this.findById(id)
                .orElseThrow(() -> new Exception("Group by id '" + id + "' not found"));
    }

    @Override
    public Group commit(Group group) {
        return this.repo.save(group);
    }


    @Override
    public List<Group> findAll() {
        return this.repo.findAll();
    }

    @Override
    public List<Subject> findAllSubjects(Group group) {
        return group.getSubjects();
    }

    @Override
    public boolean checkSubject(Group group, Subject subject) {
        return group.getSubjects().contains(subject);
    }

    @Override
    public Subject addSubject(Group group, Subject subject) {
        group.getSubjects().add(subject);
        this.repo.save(group);
        return subject;
    }

    @Override
    public Subject deleteSubject(Group group, Subject subject) {
        group.getSubjects().remove(subject);
        this.repo.save(group);
        return subject;
    }

    @Override
    public List<Group> findByName(String name) throws Exception {
        return this.repo.findByName(name).orElseThrow(
                () -> new Exception("Group by this name '" + name + "' not found"));
    }

    @Override
    public List<Group> findBySubjects(List<Subject> subjects) throws Exception {
        long[] ids = subjects.stream().mapToLong(s -> s.getId()).toArray();
        return this.repo.findBySubjects(ids).orElseThrow(() -> new Exception("Group not found"));
    }
}
