package com.learn.universityjpa.cache.component;

import com.learn.universityjpa.cache.repo.SubjectResponseRepository;
import com.learn.universityjpa.controller.model.response.SubjectResponse;
import com.learn.universityjpa.db.component.SubjectComponent;
import com.learn.universityjpa.db.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Component
public class SubjectResponseComponentImpl implements SubjectResponseComponent{
    @Autowired
    private SubjectResponseRepository repo;

    @Autowired
    private SubjectComponent studentComponent;
    @Override
    public Optional<SubjectResponse> findById(Long id) {
        return repo.findById(String.valueOf(id));
    }

    @Override
    public SubjectResponse findByIdOrDie(Long id) throws Exception {
        return findById(id).orElseThrow();
    }

    @Override
    public SubjectResponse commit(Subject subject) {
        return null;
    }

    @Override
    public List<SubjectResponse> findAll() {
        return List.of();
    }

    @Override
    public List<SubjectResponse> getSubjectsByName(String nameSubject) throws Exception {
        return List.of();
    }

    @Override
    public void deleteSubjectById(Long id) {

    }

    @Override
    public void updateSubjectById(Long id, Subject subject) throws ParseException {

    }
}
