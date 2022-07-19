package com.learn.universityjpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class Teacher
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "teacher")
public class Teacher extends Person {

    @Column(name = "category", nullable = false)
    private String category;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    List<Subject> subjects = new ArrayList<>();
    @Override
    public String toString() {
        return "teacher {" +
                "id=" + this.getId() +
                ", firstName='" + this.getFirstName() + '\'' +
                ", secondName='" + this.getSecondName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", dateBirthday=" + this.getDateBirth() +
                ", Category='" + this.getCategory() + '\'' +

                ", gender=" + this.getGender() +

                '}';
    }
}
