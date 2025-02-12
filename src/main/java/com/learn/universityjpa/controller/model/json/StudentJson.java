package com.learn.universityjpa.controller.model.json;

import com.learn.universityjpa.db.entity.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class StudentJson
 */
@Getter
@Setter
@NoArgsConstructor
public class StudentJson {
    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String dateBirth;
    private String gender;

    public StudentJson(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.secondName = student.getSecondName();
        this.lastName = student.getLastName();
        this.dateBirth = student.getDateBirth().toString();
        this.gender = student.getGender().toString();
    }
}
