package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Subject;
import com.learn.universityjpa.entity.Teacher;
import com.learn.universityjpa.exceptions.SubjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
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
    @Autowired
    private  TeacherComponent   teacherComponent;

    @Override
    public Optional<Subject> findById(Long id) {
        return this.repo.findById(id);
    }

    @Override
    public Subject findByIdOrDie(Long id) throws Exception {
        return this.repo.findById(id).orElseThrow(
                () -> new SubjectNotFoundException());
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
                () -> new SubjectNotFoundException());
    }

    @Override
    public void deleteSubjectById(Long id) {
        if (!repo.existsById(id)) {
            return;
        }
        this.repo.deleteById(id);
    }

    @Override
    public void updateSubjectById(Long id, Subject subject) throws ParseException {
        this.repo.updateSubjectById(
                id,
                subject.getName(),
                subject.getDescription()
        );
    }

    @Override
    public Teacher addTeacher(long idTeacher, long idSubject) throws Exception {
        Teacher teacher =  teacherComponent.findByIdOrDie(idTeacher);
        Subject subject =  findByIdOrDie(idSubject);
        subject.getTeachers().add(teacher);
        this.repo.save(subject);
        return teacher;
    }
}
