package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Student;
import com.learn.universityjpa.entity.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class GroupComponentImpl
 */
@RequiredArgsConstructor
@Component
public class GroupComponentImpl implements GroupComponent {
    private final GroupRepository repo;
    @Override
    public Optional<Group> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Group findByIdOrDie(Long id) throws Exception {
        return this.findById(id)
                .orElseThrow(() -> new Exception("Group by id '" + id + "' not found"));

    }

    @Override
    public Group commit(Group group) {
        return this.repo.save(group);
    }

    @Override
    public List<Student> findAllStudents(Group group) {
        return new ArrayList<>(group.getStudents());
    }

    @Override
    public List<Group> findAll() {
        return repo.findAll();
    }

  /*  @Override
    public List<Subject> findAllSubjects(Group group) {
        return new ArrayList<>(group.getSubjects());
    }*/

    @Override
    public boolean checkStudent(Group group, Student student) {
        Optional<List<Student>> optionalStudents = repo.getStudentByIdFromGroup(student.getId(), group.getId());
        if (optionalStudents.isEmpty()) {
            return false;
        }
        if (optionalStudents.get().size() > 1) {
            return false;
        }
        if (optionalStudents.get().size() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkSubject(Group group, Subject subject) {
        Optional<List<Subject>> optionalSubjects =  repo.getSubjectByIdFromGroup(subject.getId(), group.getId());
        if (optionalSubjects.isEmpty()) {
            return false;
        }
        if (optionalSubjects.get().size() > 1) {
            return false;
        }
        if (optionalSubjects.get().size() == 1) {
            return true;
        }
        return false;
    }

   /* @Override
    public Subject addSubject(Group group, Subject subject) {
        group.getSubjects().add(subject);
        return subject;
    }
*/
  /*  @Override
    public Subject deleteSubject(Group group, Subject subject) {
        group.getSubjects().remove(subject);
        return subject;
    }
*/
    @Override
    public Student addStudent(Group group, Student student) {
        group.getStudents().add(student);
        return student;
    }

    @Override
    public Student deleteStudent(Group group, Student student) {
        group.getStudents().remove(student);
        return student;
    }
}
