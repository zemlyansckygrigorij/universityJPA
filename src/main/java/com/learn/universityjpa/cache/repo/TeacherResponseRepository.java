package com.learn.universityjpa.cache.repo;

import com.learn.universityjpa.controller.model.response.TeacherResponse;
import org.springframework.data.repository.CrudRepository;

public interface TeacherResponseRepository extends CrudRepository<TeacherResponse, String> {
}
