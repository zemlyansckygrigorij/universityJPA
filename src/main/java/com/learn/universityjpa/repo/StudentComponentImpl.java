package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Student;
import com.learn.universityjpa.entity.Subject;
import com.learn.universityjpa.exceptions.PersonNotFoundException;
//import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class StudentComponentImpl
 */
//@RequiredArgsConstructor
@Component
public class StudentComponentImpl implements StudentComponent {

    private final StudentRepository repo;
    @Autowired
    private GroupComponent groupComponent;

    @Autowired
    public StudentComponentImpl(StudentRepository repo/*,GroupComponent groupComponent*/){
        this.repo = repo;
       // this.groupComponent = groupComponent;
    }
    @Override
    public Optional<Student> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Student findByIdOrDie(Long id) throws Exception {
        return this.findById(id)
                .orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public Student commit(Student student) {
        return this.repo.save(student);
    }

    public List<Student> findAll() {
        return repo.findAll();
    }

    @Override
    public Group findGroup(Student student) {
        return student.getGroup();
    }

    public List<Student> findAllFromGroup(Group group) {
        return  repo
                .getStudentsFromGroup(group.getId())
                .orElseThrow(PersonNotFoundException::new);
    }

    public List<Student> findAllByGroupId(long id) throws Exception {
        return findAllFromGroup(groupComponent.findByIdOrDie(id));
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
    public List<Student> getStudentsByName(String name) {
        return repo.getStudentsByName(name).orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public void deleteStudentById(Long id) {
        if (!repo.existsById(id)) {
            return;
        }
        repo.deleteById(id);
    }

    @Override
    public void updateStudentById(Long id, Student student) {
        this.repo.updateStudentById(
                id,
                student.getFirstName(),
                student.getSecondName(),
                student.getLastName(),
                student.getGender(),
                student.getGroup(),
                student.getDateBirth()
        );
    }
}
