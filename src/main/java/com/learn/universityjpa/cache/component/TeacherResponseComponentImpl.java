package com.learn.universityjpa.cache.component;

import com.learn.universityjpa.cache.repo.TeacherResponseRepository;
import com.learn.universityjpa.controller.model.response.SubjectResponse;
import com.learn.universityjpa.controller.model.response.TeacherResponse;
import com.learn.universityjpa.db.component.TeacherComponent;
import com.learn.universityjpa.db.entity.Subject;
import com.learn.universityjpa.db.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
@Component
public class TeacherResponseComponentImpl implements TeacherResponseComponent{
    @Autowired
    private TeacherResponseRepository repo;

    @Autowired
    private TeacherComponent studentComponent;
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
        return null;
    }

    @Override
    public List<TeacherResponse> findAll() {
        return List.of();
    }

    @Override
    public List<TeacherResponse> getTeachersByName(String name) throws Exception {
        return List.of();
    }

    @Override
    public void deleteTeacherById(Long id) {

    }

    @Override
    public void updateTeacherById(Long id, Teacher teacher) {

    }

    @Override
    public List<SubjectResponse> findAllSubjects(Teacher teacher) {
        return List.of();
    }

    @Override
    public boolean checkSubject(Teacher teacher, Subject subject) {
        return false;
    }

    @Override
    public SubjectResponse addSubject(Teacher teacher, Subject subject) {
        return null;
    }

    @Override
    public SubjectResponse deleteSubject(Teacher teacher, Subject subject) throws ParseException {
        return null;
    }

    @Override
    public SubjectResponse deleteSubject(long idTeacher, long idSubject) throws Exception {
        return null;
    }

    @Override
    public SubjectResponse addSubject(long idTeacher, long idSubject) throws Exception {
        return null;
    }
}
