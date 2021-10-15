package com.learn.universityjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher extends Person {
    @Column(name="category", nullable=false)
    private String category;
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
