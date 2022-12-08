package com.springboot.project2.controller;

import com.springboot.project2.dto.UserDTO;
import com.springboot.project2.dto.UserRoleDTO;
import com.springboot.project2.service.UserRoleService;
import com.springboot.project2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/user-role")
public class UserRoleController {
    @Autowired
    UserRoleService userRoleService;

    @GetMapping("/new")
    public String add(){
        return "user-role/add.html";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute UserRoleDTO u) throws IllegalStateException, IOException {
        userRoleService.create(u);
        return "redirect:/user-role/search";
    }
}
