package com.springboot.project2.service.impl;

import com.springboot.project2.dto.PageDTO;
import com.springboot.project2.dto.StudentDTO;
import com.springboot.project2.dto.UserDTO;
import com.springboot.project2.entity.Student;
import com.springboot.project2.entity.User;
import com.springboot.project2.entity.UserRole;
import com.springboot.project2.repo.StudentRepo;
import com.springboot.project2.repo.UserRepo;
import com.springboot.project2.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    @Transactional
    public void create(StudentDTO studentDTO) {
        //check user if role = ROLE_STUDENT
        User user = userRepo.findById(studentDTO.getId()).orElseThrow(NoResultException::new);
        for(UserRole userRole : user.getUserRoles()) {
            if (userRole.getRole().equals("ROLE_STUDENT")) {
                Student student = new Student();
                student.setStudentCode(studentDTO.getStudentCode());
                student.setId(studentDTO.getId());
                studentRepo.save(student);
            }
        }
    }

    @Override
    @Transactional
    public void update(StudentDTO studentDTO) {
        Student student = studentRepo.findById(studentDTO.getId()).orElseThrow(NoResultException::new);
        student.setStudentCode(studentDTO.getStudentCode());
        studentRepo.save(student);
    }

    @Override
    @Transactional
    public void delete(int id) {
       Student student = studentRepo.findById(id).orElseThrow(NoResultException::new); //java8 lambda

        if(student!=null){
            studentRepo.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void deleteAll(List<Integer> ids) {
        studentRepo.deleteAllById(ids);
    }

    @Override
    @Transactional
    public StudentDTO getById(int id) {
        return null;
    }

    @Override
    @Transactional
    public PageDTO<StudentDTO> search(String name, String studentCode, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> pageRS = null;

        //thu vien check null, empty... cua String

        if(StringUtils.hasText(name) && StringUtils.hasText(studentCode)){
            pageRS = studentRepo.searchByNameAndCode(name,studentCode,pageable);
        }else if(StringUtils.hasText(studentCode)){
            pageRS = studentRepo.searchByStudentCode(studentCode, pageable);
        }else if(StringUtils.hasText(name)){
            pageRS = studentRepo.searchByName(name,pageable);
        }
        else{
            pageRS = studentRepo.findAll(pageable);
        }

        PageDTO<StudentDTO> pageDTO = new PageDTO<>();
        pageDTO.setTotalPages(pageRS.getTotalPages());
        pageDTO.setTotalElements(pageRS.getTotalElements());

        List<StudentDTO> studentDTOS = new ArrayList<>();
        for(Student student:pageRS.getContent()){
            studentDTOS.add(new ModelMapper().map(student,StudentDTO.class));
        }
        pageDTO.setContents(studentDTOS); //set vao pageDto
        return pageDTO;
    }
}
