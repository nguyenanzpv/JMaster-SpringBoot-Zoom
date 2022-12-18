package com.springboot.project2.repo;

import com.springboot.project2.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;

public interface GroupRepo extends JpaRepository<Group,Integer> {
    @Query("SELECT g FROM Group g JOIN g.users u WHERE u.name LIKE :name")
    Page<Group> searchByName(@Param("name") String name, Pageable pageable);
}
