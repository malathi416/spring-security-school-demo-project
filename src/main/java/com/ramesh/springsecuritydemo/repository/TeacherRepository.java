package com.ramesh.springsecuritydemo.repository;

import com.ramesh.springsecuritydemo.model.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher,Integer> {
}
