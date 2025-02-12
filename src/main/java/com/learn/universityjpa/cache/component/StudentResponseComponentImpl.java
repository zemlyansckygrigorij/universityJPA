package com.learn.universityjpa.cache.component;

import com.learn.universityjpa.cache.repo.StudentResponseRepository;
import com.learn.universityjpa.controller.model.response.StudentResponse;
import com.learn.universityjpa.db.component.StudentComponent;
import com.learn.universityjpa.db.entity.Student;
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
 * class StudentResponseComponentImpl for work with cache repository
 */
@Component
public class StudentResponseComponentImpl implements StudentResponseComponent {
    @Autowired
    private StudentResponseRepository repo;

    @Autowired
    private StudentComponent studentComponent;

    @Override
    public Optional<StudentResponse> findById(Long id) {
        return repo.findById(String.valueOf(id));
    }

    @Override
    public List<StudentResponse> findAll() {
        List<StudentResponse> students = new ArrayList<>();
        repo.findAll().forEach(students::add);
        return students;
    }

    @Override
    public StudentResponse findByIdOrDie(Long id) throws Exception {
        return findById(id).orElseThrow();
    }

    @Override
    public StudentResponse commit(Student student) {
        return repo.save(new StudentResponse(studentComponent.commit(student)));
    }

    @Override
    public void deleteStudentById(Long id) throws Exception {
        studentComponent.deleteStudentById(id);
        repo.deleteById(String.valueOf(id));
    }

   @Override
    public void updateStudentById(Long id, Student student) throws ParseException {
       studentComponent.updateStudentById(id,  student);
       student.setId(id);
       repo.save(new StudentResponse(student));
    }

    @Override
    public List<StudentResponse> findStudentsByName(String name) throws Exception {
        return findAll()
                .stream()
                .filter(s->s.getFirstName().contains(name))
                .collect(Collectors.toList());
    }
}
