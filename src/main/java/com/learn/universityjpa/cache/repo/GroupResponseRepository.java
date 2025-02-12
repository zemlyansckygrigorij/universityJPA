package com.learn.universityjpa.cache.repo;

import com.learn.universityjpa.controller.model.response.GroupResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * repository GroupResponseRepository for work with cache repository
 */
@Repository
public interface GroupResponseRepository extends CrudRepository<GroupResponse, String> {
}
