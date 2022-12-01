package com.springboot.project2.repo;

import com.springboot.project2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

}
