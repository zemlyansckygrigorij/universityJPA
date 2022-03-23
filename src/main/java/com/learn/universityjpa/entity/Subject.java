package com.learn.universityjpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @Column(name = "id_subject", nullable = false)
    private Long id;
    @Column(name = "name_subject", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
}
