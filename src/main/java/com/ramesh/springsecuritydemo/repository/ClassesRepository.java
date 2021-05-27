package com.ramesh.springsecuritydemo.repository;

import com.ramesh.springsecuritydemo.model.Classes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository extends CrudRepository<Classes,Integer> {
}
