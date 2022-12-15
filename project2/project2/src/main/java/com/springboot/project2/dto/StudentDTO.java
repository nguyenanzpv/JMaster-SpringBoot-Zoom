package com.springboot.project2.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private int id;
    private String studentCode;
    private UserDTO user;
}
