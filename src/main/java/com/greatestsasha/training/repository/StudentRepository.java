package com.greatestsasha.training.repository;

import com.greatestsasha.training.model.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
}