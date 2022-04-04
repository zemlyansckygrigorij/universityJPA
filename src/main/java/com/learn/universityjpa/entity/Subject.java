package com.learn.universityjpa.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class Subject
 */
@Entity
@Getter
@Setter
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "group_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    List<Group> groups;
}
