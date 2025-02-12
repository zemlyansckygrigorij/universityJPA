package com.learn.universityjpa.cache.component;

import com.learn.universityjpa.cache.repo.SubjectResponseRepository;
import com.learn.universityjpa.controller.model.response.SubjectResponse;
import com.learn.universityjpa.db.component.SubjectComponent;
import com.learn.universityjpa.db.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class SubjectResponseComponentImpl for work with cache repository
 */
@Component
public class SubjectResponseComponentImpl implements SubjectResponseComponent {
    @Autowired
    private SubjectResponseRepository repo;

    @Autowired
    private SubjectComponent subjectComponent;
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
        return repo.save(new SubjectResponse(subjectComponent.commit(subject)));
    }

    @Override
    public List<SubjectResponse> findAll() {
        List<SubjectResponse> subjects = new ArrayList<>();
        repo.findAll().forEach(subjects::add);
        return subjects;
    }

    @Override
    public List<SubjectResponse> getSubjectsByName(String name) throws Exception {
        return findAll()
                .stream()
                .filter(s->s.getName().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSubjectById(Long id) {
        subjectComponent.deleteSubjectById(id);
        repo.deleteById(String.valueOf(id));
    }

    @Override
    public void updateSubjectById(Long id, Subject subject) throws ParseException {
        subjectComponent.updateSubjectById(id,  subject);
        subject.setId(id);
        repo.save(new SubjectResponse(subject));
    }
}
