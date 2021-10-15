package com.learn.universityjpa.entity;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
@Setter
public abstract class Person {
    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue
    private Long id;

    private String firstName;
    private String secondName;
    private String lastName;
    private Date dateBirth;
    private Gender gender;
}
