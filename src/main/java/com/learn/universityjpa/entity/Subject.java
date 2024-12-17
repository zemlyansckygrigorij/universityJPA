package com.learn.universityjpa.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "group_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    List<Group> groups;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "teacher_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    List<Teacher> teachers;

    @Override
    public String toString() {
        return "subject {"
                +"id=" + this.getId()
                +", name='" + this.name+ '\''
                +", description='" + this.description + '\''
                +" groups-["+groups.stream().map(g->"  "+g.getId()+"-"+g.getName()).collect(Collectors.toSet())+"] "
                +" teachers-["+teachers.stream().map(t->"  "+t.getId()+"-"+t.getFirstName()+" "+t.getSecondName()+" "+t.getLastName()).collect(Collectors.toSet())+"] "+
                '}';
    }
}
