package com.learn.universityjpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="name", nullable=false)
    private String name;
    @Column(name="description", nullable=false)
    private String description;
}
