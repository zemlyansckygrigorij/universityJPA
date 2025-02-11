package com.learn.universityjpa.controller.model.json;

import com.learn.universityjpa.controller.model.response.TeacherResponse;
import com.learn.universityjpa.db.entity.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class TeacherJson
 */
@Getter
@Setter
@NoArgsConstructor
public class TeacherJson {
    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String dateBirth;
    private String gender;
    private String category;

    public TeacherJson(Teacher teacher) {
        this.id = teacher.getId();
        this.firstName = teacher.getFirstName();
        this.secondName = teacher.getSecondName();
        this.lastName = teacher.getLastName();
        this.dateBirth = teacher.getDateBirth().toString();
        this.gender = teacher.getGender().toString();
        this.category = teacher.getCategory();
    }

    public TeacherJson(TeacherResponse response) {
        this.id = response.getId();
        this.firstName = response.getFirstName();
        this.secondName = response.getSecondName();
        this.lastName = response.getLastName();
        this.dateBirth = response.getDateBirth().toString();
        this.gender = response.getGender().toString();
        this.category = response.getCategory();
    }
 /*   public TeacherResponse getTeacherResponse(){
        TeacherResponse response = new  TeacherResponse();
        response.setId(this.id);
        response.setFirstName(this.firstName);
        response.setSecondName(this.secondName);
        response.setLastName(this.lastName);
        response.setDateBirth(this.dateBirth);
        response.setGender(this.gender);
        response.setCategory(this.category);

        return response;
    }*/
}
