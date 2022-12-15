package com.springboot.project2.controller;

import com.springboot.project2.dto.ResponseDTO;
import com.springboot.project2.dto.StudentDTO;
import com.springboot.project2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentRESTController {
    @Autowired
    StudentService studentService;

    @PostMapping("/new")
    public ResponseDTO<StudentDTO> add(@RequestBody StudentDTO studentDTO){
        studentService.create(studentDTO);
        return ResponseDTO.<StudentDTO>builder().status(200).data(studentDTO).build();
    }
}
