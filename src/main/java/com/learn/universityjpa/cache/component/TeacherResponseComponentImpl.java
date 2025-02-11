package com.learn.universityjpa.cache.component;

import com.learn.universityjpa.cache.repo.SubjectResponseRepository;
import com.learn.universityjpa.cache.repo.TeacherResponseRepository;
import com.learn.universityjpa.controller.model.json.SubjectJson;
import com.learn.universityjpa.controller.model.json.TeacherJson;
import com.learn.universityjpa.controller.model.response.SubjectResponse;
import com.learn.universityjpa.controller.model.response.TeacherResponse;
import com.learn.universityjpa.db.component.TeacherComponent;
import com.learn.universityjpa.db.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class TeacherResponseComponentImpl for work with cache repository
 */
@Component
public class TeacherResponseComponentImpl implements TeacherResponseComponent {
    @Autowired
    private TeacherResponseRepository repo;
    @Autowired
    private SubjectResponseRepository subjectRepo;

    @Autowired
    private SubjectResponseComponent subjectResponseComponent;
    @Autowired
    private TeacherComponent component;
    @Override
    public Optional<TeacherResponse> findById(Long id) {
        return repo.findById(String.valueOf(id));
    }

    @Override
    public TeacherResponse findByIdOrDie(Long id) throws Exception {
        return findById(id).orElseThrow();
    }

    @Override
    public TeacherResponse commit(Teacher teacher) {
        return repo.save(new TeacherResponse(component.commit(teacher)));
    }

    @Override
    public List<TeacherResponse> findAll() {
        return List.of();
    }

    @Override
    public List<TeacherResponse> getTeachersByName(String name) throws Exception {
        List<TeacherResponse> teachers = new ArrayList<>();
        repo.findAll().forEach(teachers::add);
        return teachers;
    }

    @Override
    public void deleteTeacherById(Long id) {
        component.deleteTeacherById(id);
        repo.deleteById(String.valueOf(id));
    }

    @Override
    public void updateTeacherById(Long id, Teacher teacher) {
        component.updateTeacherById(id, teacher);
        teacher.setId(id);
        repo.save(new TeacherResponse(teacher));
    }

    @Override
    public List<SubjectJson> findAllSubjects(Long id) throws Exception {
        return findByIdOrDie(id).getSubjects();
    }

    @Override
    public boolean checkSubject(Long teacherId, Long subjectid) throws Exception {
       return findByIdOrDie(teacherId).getSubjects().stream().anyMatch(s-> s.getId().equals(subjectid));
    }


    @Override
    public SubjectJson deleteSubject(Long teacherId, Long subjectid) throws Exception {
        if (!checkSubject(teacherId, subjectid)) {
            throw new Exception();
        }

        TeacherResponse teacherResponse = findByIdOrDie(teacherId);
        SubjectResponse subjectResponse =  subjectResponseComponent.findByIdOrDie(subjectid);
        SubjectJson subjectJson = teacherResponse
                .getSubjects()
                .stream()
                .filter(s->s.getId().equals(subjectid))
                .findFirst().get();
        teacherResponse.getSubjects().remove(subjectJson);

        subjectResponse.getTeachers().remove(subjectResponse.getTeachers()
                .stream()
                .filter(t->t.getId().equals(teacherId))
                .findFirst().get());
        subjectRepo.save(subjectResponse);
        component.deleteSubject(teacherId , subjectid);
        repo.save(teacherResponse);
        return subjectJson;
    }

    @Override
    public SubjectJson addSubject(Long teacherId, Long subjectid) throws Exception {
        if (checkSubject(teacherId, subjectid)) {
            throw new Exception();
        }

        TeacherResponse teacherResponse = findByIdOrDie(teacherId);
        SubjectResponse subjectResponse =  subjectResponseComponent.findByIdOrDie(subjectid);
        SubjectJson subjectJson = new SubjectJson(subjectResponse);

        teacherResponse.getSubjects().add(subjectJson);

        subjectResponse.getTeachers().add(new TeacherJson(teacherResponse));
        subjectRepo.save(subjectResponse);
        component.deleteSubject(teacherId, subjectid);
        repo.save(teacherResponse);
        return subjectJson;
    }
}
