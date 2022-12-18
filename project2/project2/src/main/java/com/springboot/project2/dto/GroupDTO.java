package com.springboot.project2.dto;

import com.springboot.project2.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class GroupDTO {
    private Integer id;

    private String name;

    private List<UserDTO> users;
}
