package com.learn.universityjpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@ToString
@Getter
@Setter
public abstract class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String secondName;
    private String lastName;
    private Date dateBirth;
    private Gender gender;
}
