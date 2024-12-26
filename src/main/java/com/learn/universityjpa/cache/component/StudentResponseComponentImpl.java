package com.learn.universityjpa.cache.component;

import com.learn.universityjpa.cache.repo.StudentResponseRepository;
import com.learn.universityjpa.controller.model.response.StudentResponse;
import com.learn.universityjpa.db.entity.Student;
import com.learn.universityjpa.db.component.StudentComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class GroupComponentImpl
 */
@Component
public class StudentResponseComponentImpl implements StudentResponseComponent{
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
        StudentResponse sr = new StudentResponse(student);
        return repo.save(sr);
    }

    @Override
    public void deleteStudentById(Long id) throws Exception {
        studentComponent.deleteStudentById(id);
        repo.deleteById(String.valueOf(id));
    }

   @Override
    public void updateStudentById(Long id, Student student) {
        repo.save(new StudentResponse(student));
    }
}
