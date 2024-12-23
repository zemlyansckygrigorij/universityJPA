package com.learn.universityjpa.cache.repo;

import com.learn.universityjpa.controller.model.response.StudentResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentResponseRepository   extends CrudRepository<StudentResponse, String> {
}
