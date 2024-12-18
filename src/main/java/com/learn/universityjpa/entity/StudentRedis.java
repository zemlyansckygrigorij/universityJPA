package com.learn.universityjpa.entity;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Student")
public class StudentRedis implements Serializable {

    public StudentRedis(String id, String name, Gender gender, int grade) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
    }

    public enum Gender {
        MALE, FEMALE
    }

    private final String id;
    private final String name;
    private final Gender gender;
    private final int grade;
    public String ToString(){
        return "id-"+id+" name-"+name+" gender- "+gender +" grade-" +grade;
    }
}