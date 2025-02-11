package com.learn.universityjpa.cache.repo;

import com.learn.universityjpa.controller.model.response.TeacherResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * repository TeacherResponseRepository for work with cache repository
 */
@Repository
public interface TeacherResponseRepository extends CrudRepository<TeacherResponse, String> {
}
