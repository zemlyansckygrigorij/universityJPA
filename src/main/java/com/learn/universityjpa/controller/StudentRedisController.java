package com.learn.universityjpa.controller;
import com.learn.universityjpa.entity.StudentRedis;
import com.learn.universityjpa.repo.StudentRedisServiceImple;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
@Tag(name = "API работы со студентами")
@RequestMapping("/studentredis")
@RequiredArgsConstructor
public class StudentRedisController {
     private final StudentRedisServiceImple component;

    @GetMapping()
    public List<StudentRedis> getAllStudents() {
        return component.findAll();
    }

    @GetMapping("/{id}")
    public StudentRedis getStudentById(
            @PathVariable(name = "id") final long id
    ) {
        return component.findById(String.valueOf(id));
    }
}
