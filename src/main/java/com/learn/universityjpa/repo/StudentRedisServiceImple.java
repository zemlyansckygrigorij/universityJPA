package com.learn.universityjpa.repo;

import com.learn.universityjpa.entity.StudentRedis;
import com.learn.universityjpa.entity.StudentRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentRedisServiceImple {
    @Autowired
    StudentRedisRepository repo;

public StudentRedis save(StudentRedis student){
    return repo.save(student);
}
public StudentRedis findById(String id){
    return repo.findById(id).get();
}

public void deleteById(String id){
    repo.deleteById(id);
}
public List<StudentRedis> findAll(){
    List<StudentRedis> students = new ArrayList<>();
    repo.findAll().forEach(students::add);
    return students;
}
}