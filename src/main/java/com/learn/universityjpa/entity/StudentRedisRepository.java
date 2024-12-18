package com.learn.universityjpa.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  StudentRedisRepository  extends CrudRepository<StudentRedis, String> {}
