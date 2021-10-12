package com.learn.universityjpa.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
}
