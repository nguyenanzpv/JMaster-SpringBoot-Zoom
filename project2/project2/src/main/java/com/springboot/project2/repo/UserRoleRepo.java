package com.springboot.project2.repo;

import com.springboot.project2.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepo extends JpaRepository<UserRole,Integer> {
}
