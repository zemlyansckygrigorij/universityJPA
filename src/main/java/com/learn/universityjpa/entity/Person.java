package com.learn.universityjpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author Grigoriy Zemlyanskiy
 *
 * @version 1.0
 * abstract class Person
 */
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
@Getter
@Setter
@TypeDef(
        name = "pgsql_enum",
        typeClass = GenderConverter.class
)
public abstract class Person {
    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue
    private Long id;

    private String firstName;
    private String secondName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date dateBirth;
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private Gender gender;
}
