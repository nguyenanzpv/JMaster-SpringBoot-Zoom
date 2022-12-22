package com.springboot.project2.dto;

import com.springboot.project2.entity.User;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class GroupDTO {
    private Integer id;

    @NotBlank
    @Size(min=6)
    private String name;

    private List<UserDTO> users;
}
