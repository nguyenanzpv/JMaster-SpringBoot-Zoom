package com.springboot.project2.service.impl;

import com.springboot.project2.dto.PageDTO;
import com.springboot.project2.dto.StudentDTO;
import com.springboot.project2.entity.Student;
import com.springboot.project2.repo.StudentRepo;
import com.springboot.project2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepo studentRepo;

    @Override
    @Transactional
    public void create(StudentDTO studentDTO) {
        Student student = new Student();
        student.setStudentCode(studentDTO.getStudentCode());
        student.setId(studentDTO.getId());
        studentRepo.save(student);
    }

    @Override
    public void update(StudentDTO studentDTO) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll(List<Integer> ids) {

    }

    @Override
    public StudentDTO getById(int id) {
        return null;
    }

    @Override
    public PageDTO<StudentDTO> searchByName(String name, int page, int size) {
        return null;
    }
}
