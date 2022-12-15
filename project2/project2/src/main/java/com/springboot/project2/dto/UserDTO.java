package com.springboot.project2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
public class UserDTO {
    private Integer id;
    @NotBlank
    private String name;
    private String avatar;// URL
    private String username;
    private String password;

    @JsonFormat(pattern="dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthdate;

    @JsonIgnore
    private MultipartFile file;

//	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date createdAt;

    //lay gia tri user role theo user
    //@JsonManagedReference
    private List<UserRoleDTO> userRoles;
}
