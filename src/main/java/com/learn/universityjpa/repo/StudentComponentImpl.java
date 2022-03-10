package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Student;
import com.learn.universityjpa.entity.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class StudentComponentImpl implements StudentComponent {
    private final StudentRepository repo;
    @Override
    public Optional<Student> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Student findByIdOrDie(Long id) throws Exception {
        return this.findById(id)
                .orElseThrow(() -> new Exception("Student by id '" + id + "' not found"));
    }

    @Override
    public Student commit(Student student) {
        return repo.save(student);
    }

    @Override
    public Group findGroup(Student student) {
        return student.getGroup();
    }

    @Override
    public List<Subject> findAllSubjects(Student student) {
        return student.getGroup().getSubjects();
    }

    @Override
    public boolean checkSubject(Student student, Subject subject) {
        return student.getGroup().getSubjects().contains(subject);
    }
}
