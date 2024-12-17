package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Subject;
import com.learn.universityjpa.exceptions.SubjectNotFoundException;
import lombok.RequiredArgsConstructor;
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

    private final SubjectRepository repo;

    @Override
    public Optional<Subject> findById(Long id) {
        return this.repo.findById(id);
    }

    @Override
    public Subject findByIdOrDie(Long id) throws Exception {
        return this.repo.findById(id).orElseThrow(SubjectNotFoundException::new);
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
    public List<Subject> getSubjectsByName(String nameSubject) {
        return this.repo.getSubjectsByName(nameSubject).orElseThrow(SubjectNotFoundException::new);
    }

    @Override
    public void deleteSubjectById(Long id) {
        if (!repo.existsById(id)) {
            return;
        }
        this.repo.deleteById(id);
    }

    @Override
    public void updateSubjectById(Long id, Subject subject) {
        this.repo.updateSubjectById(
                id,
                subject.getName(),
                subject.getDescription()
        );
    }
}
