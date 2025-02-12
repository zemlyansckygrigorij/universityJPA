package com.learn.universityjpa.cache.repo;

import com.learn.universityjpa.controller.model.response.SubjectResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * repository SubjectResponseRepository for work with cache repository
 */
@Repository
public interface SubjectResponseRepository extends CrudRepository<SubjectResponse, String> {
}
