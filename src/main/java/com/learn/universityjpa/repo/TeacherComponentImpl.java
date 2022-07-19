package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Subject;
import com.learn.universityjpa.entity.Teacher;
import com.learn.universityjpa.exceptions.PersonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class TeacherComponentImpl
 */
@RequiredArgsConstructor
@Component
public class TeacherComponentImpl implements TeacherComponent {
    @Autowired
    private  SubjectComponent   subjectComponent;
    @Autowired
    TeacherRepository repo;
    @Autowired
    SubjectRepository subjectRepo;
    @Override
    public Optional<Teacher> findById(Long id) {
        return this.repo.findById(id);
    }

    @Override
    public Teacher findByIdOrDie(Long id) throws Exception {
        return this.repo.findById(id).orElseThrow(
                ()-> new PersonNotFoundException());
    }

    @Override
    public Teacher commit(Teacher teacher) {
        return this.repo.save(teacher);
    }

    @Override
    public List<Teacher> findAll() {
        return this.repo.findAll();
    }

    @Override
    public List<Teacher> getTeachersByName(String name) throws Exception {
        return  this.repo.getTeachersByName(name).orElseThrow(
                () -> new PersonNotFoundException());
    }

    @Override
    public void deleteTeacherById(Long id) {
        this.repo.deleteById(id);
    }

    @Override
    public void updateTeacherById(Long id, Teacher teacher) {
        int result = this.repo.updateTeacherById(
                teacher.getFirstName(),
                teacher.getSecondName(),
                teacher.getLastName(),
                teacher.getDateBirth(),
                teacher.getCategory(),
                teacher.getGender(),
                id);
    }

    @Override
    public List<Subject> findAllSubjects(Teacher teacher) {
        return teacher.getSubjects();
    }

    @Override
    public boolean checkSubject(Teacher teacher, Subject subject) {
        return teacher.getSubjects().contains(subject);
    }

    @Override
    public Subject addSubject(Teacher teacher, Subject subject) {
        if (teacher.getSubjects().contains(subject)) {
            return subject;
        }
        subject.getTeachers().add(teacher);
        teacher.getSubjects().add(subject);
        this.repo.save(teacher);
        this.subjectRepo.save(subject);
        return subject;
    }
    public Subject addSubject(long idTeacher, long idSubject) throws Exception {
        Teacher teacher =  findByIdOrDie(idTeacher);
        Subject subject = subjectComponent.findByIdOrDie(idSubject);
        teacher.getSubjects().add(subject);
        subject.getTeachers().add(teacher);
        this.repo.save(teacher);
        this.subjectRepo.save(subject);
        return subject;
    }
    public Subject deleteSubject(long idTeacher, long idSubject) throws Exception {
        Teacher teacher = findByIdOrDie(idTeacher);
        Subject subject = subjectComponent.findByIdOrDie(idSubject);
        subject.getTeachers().remove(teacher);
        teacher.getSubjects().remove(subject);
        this.subjectRepo.save(subject);
        this.repo.save(teacher);
        return subject;
    }
    @Override
    public Subject deleteSubject(Teacher teacher, Subject subject) throws ParseException {
        if (!teacher.getSubjects().contains(subject)) {
            return subject;
        }
        subject.getTeachers().remove(teacher);
        teacher.getSubjects().remove(subject);
        this.subjectRepo.save(subject);
        this.repo.save(teacher);
        return subject;
    }
}
