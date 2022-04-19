package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Teacher;
import com.learn.universityjpa.exceptions.PersonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    TeacherRepository repo;

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
        return repo.getTeachersByName(name).orElseThrow(
                () -> new PersonNotFoundException());
    }
}
