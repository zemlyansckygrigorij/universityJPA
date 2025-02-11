package com.learn.universityjpa.cache.component;

import com.learn.universityjpa.cache.repo.GroupResponseRepository;
import com.learn.universityjpa.controller.model.json.StudentJson;
import com.learn.universityjpa.controller.model.json.SubjectJson;
import com.learn.universityjpa.controller.model.response.GroupResponse;
import com.learn.universityjpa.db.component.GroupComponent;
import com.learn.universityjpa.db.entity.Group;
import com.learn.universityjpa.db.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class GroupResponseComponentImpl for work with cache repository
 */
@Component
public class GroupResponseComponentImpl implements GroupResponseComponent {
    @Autowired
    private GroupResponseRepository repo;

    @Autowired
    private GroupComponent groupComponent;

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
        return repo.save(new GroupResponse(groupComponent.commit(group)));
    }

    @Override
    public List<GroupResponse> findAll() {
        List<GroupResponse> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public List<SubjectJson> findAllSubjectsByGroupId(Long id) throws Exception {
        return findByIdOrDie(id)
                .getSubjects();
    }

    @Override
    public boolean checkSubjectByGroupId(Long id, String subjectName) throws Exception {
        return findByIdOrDie(id)
                .getSubjects()
                .stream().anyMatch((x)->x.getName().toLowerCase().contains(subjectName.toLowerCase()));
    }

    @Override
    public SubjectJson addSubject(Long groupId, Long subjectId) throws Exception {
        SubjectJson subjectJson = new SubjectJson(groupComponent.addSubject(groupId, subjectId));
        GroupResponse groupResponse = findByIdOrDie(groupId);
        groupResponse.getSubjects().add(subjectJson);
        repo.save(groupResponse);
        return subjectJson;
    }

    @Override
    public SubjectJson deleteSubject(Long groupId, Long subjectId) throws Exception {
        SubjectJson subjectJson = new SubjectJson(groupComponent.deleteSubject(groupId, subjectId));
        GroupResponse groupResponse = findByIdOrDie(groupId);

        List<SubjectJson> subjectJsonListById = groupResponse
                .getSubjects()
                .stream()
                .filter(s->s.getId().equals(subjectId))
                .toList();

        groupResponse.getSubjects().removeAll(subjectJsonListById);
        repo.deleteById(String.valueOf(groupId));
        repo.save(groupResponse);
        return subjectJson;
    }

    public  List<StudentJson> findAllStudentsByGroupId(Long id) throws Exception {
        return findByIdOrDie(id).getStudents();
    }

    @Override
    public List<GroupResponse> findByName(String name) throws Exception {
        return findAll().stream().filter(g->g.getName().contains(name)).toList();
    }

    @Override
    public List<GroupResponse> findBySubjects(List<Subject> subjects) throws Exception {
        List<SubjectJson> subjectJsonList = new ArrayList<>();
        subjects.forEach(s->subjectJsonList.add(new SubjectJson(s)));
        return findAll().stream().filter(g->g.getSubjects().contains(subjectJsonList)).toList();
    }

    @Override
    public void deleteGroupById(Long id) throws Exception {
        groupComponent.deleteGroupById(id);
        repo.deleteById(String.valueOf(id));
    }

    @Override
    public void updateGroupById(Long id, Group group) {
        groupComponent.updateGroupById(id, group);
        group.setId(id);
        repo.save(new GroupResponse(group));
    }
}
