package com.learn.universityjpa.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "group")
public class Group {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;
    @Column(name="name", nullable=false)
    private String name;
    @Column(name="specification", nullable=false)
    private String specification;
    @OneToMany(mappedBy = "student")
    private List<Student> students;
}
