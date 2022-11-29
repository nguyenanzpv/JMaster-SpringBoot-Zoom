package com.java.demo.repo;

import com.java.demo.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepo extends JpaRepository<Person,Integer> {
    @Query("Select p from Person p where p.age >= :min and p.age <= :max")
    List<Person> search(@Param("min") int min, @Param("max") int max);
    //tu generate query cho minh - where name =
    List<Person> findByName(String name);
    //phan trang
    Page<Person> findByName(String name, Pageable page);
    @Query("Select p from Person p where p.age >= :min and p.age <= :max")
    Page<Person> search(@Param("min") int min, @Param("max") int max,Pageable page);
}
