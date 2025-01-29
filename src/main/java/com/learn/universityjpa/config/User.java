package com.learn.universityjpa.config;


import lombok.Data;
import javax.persistence.Table;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String password;

    @Column
    private String role;
}