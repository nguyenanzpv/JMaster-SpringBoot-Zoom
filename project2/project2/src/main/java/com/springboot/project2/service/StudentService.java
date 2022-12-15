package com.springboot.project2.service;

import com.springboot.project2.dto.PageDTO;
import com.springboot.project2.dto.StudentDTO;
import com.springboot.project2.dto.UserDTO;

import java.util.List;

public interface StudentService {
    public void create(StudentDTO studentDTO);
    public void update(StudentDTO studentDTO);
    public void delete(int id);
    public void deleteAll(List<Integer> ids);
    public StudentDTO getById(int id);
    public PageDTO<StudentDTO> searchByName(String name, int page, int size);
}
