package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Student;
import com.learn.universityjpa.entity.Subject;
import com.learn.universityjpa.exceptions.PersonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class StudentComponentImpl
 */
@RequiredArgsConstructor
@Component
public class StudentComponentImpl implements StudentComponent {

    private final StudentRepository repo;
    private final GroupComponent groupComponent;

    @Override
    public Optional<Student> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Student findByIdOrDie(Long id) throws Exception {
        return this.findById(id)
                .orElseThrow(() -> new PersonNotFoundException());
    }

    @Override
    public Student commit(Student student) {
        return this.repo.save(student);
    }

    public List<Student> findAll() {
        return  repo.findAll();
    }
    @Override
    public Group findGroup(Student student) {
        return student.getGroup();
    }

    public List<Student> findAllFromGroup(Group group) throws Exception {
        return  repo.getStudentsFromGroup(group.getId()).orElseThrow(
                () -> new PersonNotFoundException());
    }

   @Override
    public List<Subject> findAllSubjects(Student student) {
        return student.getGroup().getSubjects();
    }

    @Override
    public boolean checkSubject(Student student, Subject subject) {
        return student.getGroup().getSubjects().contains(subject);
    }
    @Override
    public List<Student> getStudentsByName(String name) throws Exception {
        return repo.getStudentsByName(name).orElseThrow(
                () -> new PersonNotFoundException());
    }
}
