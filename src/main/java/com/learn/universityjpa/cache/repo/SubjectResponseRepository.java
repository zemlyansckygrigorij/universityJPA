package com.learn.universityjpa.cache.repo;

import com.learn.universityjpa.controller.model.response.SubjectResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectResponseRepository extends CrudRepository<SubjectResponse, String> {
}
