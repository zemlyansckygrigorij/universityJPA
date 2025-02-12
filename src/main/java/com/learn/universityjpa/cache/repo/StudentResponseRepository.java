package com.learn.universityjpa.cache.repo;

import com.learn.universityjpa.controller.model.response.StudentResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 *repository StudentResponseRepository for work with cache repository
 */
@Repository
public interface StudentResponseRepository extends CrudRepository<StudentResponse, String> {
}
