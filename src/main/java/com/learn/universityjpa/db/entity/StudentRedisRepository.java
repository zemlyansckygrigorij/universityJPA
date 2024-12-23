package com.learn.universityjpa.db.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  StudentRedisRepository  extends CrudRepository<StudentRedis, String> {}
