package com.learn.universityjpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class Student
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Table(name = "student")
@NoArgsConstructor
public class Student extends Person {

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
}
