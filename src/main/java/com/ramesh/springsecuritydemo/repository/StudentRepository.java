package com.ramesh.springsecuritydemo.repository;

import com.ramesh.springsecuritydemo.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {
}
