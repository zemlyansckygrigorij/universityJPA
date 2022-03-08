package com.learn.universityjpa.entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "groups")
public class Group {
    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue
    private Long id;
    @Column(name="name", nullable=false)
    private String name;
    @Column(name="specification", nullable=false)
    private String specification;
    @OneToMany(targetEntity=Student.class, mappedBy="id",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students;
    @ManyToMany(targetEntity=Subject.class, mappedBy="id",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subject> subjects;
}
