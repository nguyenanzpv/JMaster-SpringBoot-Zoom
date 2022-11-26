package com.java.demo.service;

import com.java.demo.entity.Person;
import com.java.demo.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepo personRepo;

    public List<Person> getAll(){
        return personRepo.findAll();
    }
}
