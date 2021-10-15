package com.learn.universityjpa.entity;

import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

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
    @Type( type = "pgsql_enum" )
    private Gender gender;
}
