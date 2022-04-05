package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class SubjectComponentImpl
 */
@RequiredArgsConstructor
@Component
public class SubjectComponentImpl implements  SubjectComponent {

    @Autowired
    SubjectRepository repo;

    @Override
    public Optional<Subject> findById(Long id) {
        return this.repo.findById(id);
    }

    @Override
    public Subject findByIdOrDie(Long id) throws Exception {
        return this.repo.findById(id).orElseThrow(() -> new Exception("Subject by id '" + id + "' not found"));
    }

    @Override
    public Subject commit(Subject subject) {
        return this.repo.save(subject);
    }

    @Override
    public List<Subject> findAll() {
        return this.repo.findAll();
    }

    @Override
    public List<Subject> getSubjectsByName(String nameSubject) throws Exception {
        return this.repo.getSubjectsByName(nameSubject).orElseThrow(
                () -> new Exception("Subjects by name '" + nameSubject + "' not found"));
    }
}
