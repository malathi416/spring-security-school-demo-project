package com.ramesh.springsecuritydemo.repository;

import com.ramesh.springsecuritydemo.dto.UserRegistrationDto;
import com.ramesh.springsecuritydemo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
        User findByEmail(String email);
//        User save(UserRegistrationDto userRegistrationDto);
//        User findByUsername(String firstName);

}
