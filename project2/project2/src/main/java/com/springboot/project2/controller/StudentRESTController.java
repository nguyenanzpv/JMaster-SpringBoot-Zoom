package com.springboot.project2.controller;

import com.springboot.project2.dto.PageDTO;
import com.springboot.project2.dto.ResponseDTO;
import com.springboot.project2.dto.StudentDTO;
import com.springboot.project2.dto.UserDTO;
import com.springboot.project2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

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

    @PutMapping("/edit")
    public ResponseDTO<StudentDTO> update(@RequestBody StudentDTO studentDTO){
        studentService.update(studentDTO);
        return ResponseDTO.<StudentDTO>builder().status(200).data(studentDTO).build();
    }

    @PostMapping("/search")
    public ResponseDTO<PageDTO<StudentDTO>> search(
            @RequestParam(name ="name", required=false) String name,
            @RequestParam(name = "studentCode", required = false) String studentCode,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "page", required = false) Integer page,
            Model model
    )
    {
        size = size == null ? 10 : size;
        page = page == null ? 0 : page;

       return ResponseDTO.<PageDTO<StudentDTO>>builder().status(200).data(studentService.search(name,studentCode,size,page)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable("id") int id) {
        studentService.delete(id);
        return ResponseDTO.<Void>builder().status(200)
                .build();
    }

    @GetMapping("/get/{id}")//get/10
    public ResponseDTO<StudentDTO> get(@PathVariable("id") int id) {
        StudentDTO studentDTO = studentService.getById(id);
        return ResponseDTO.<StudentDTO>builder().status(200)
                .data(studentDTO).build();
    }

}
