package com.springboot.project2.controller;

import com.springboot.project2.entity.User;
import com.springboot.project2.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepo userRepo;

    @GetMapping("/new")
    public String add(){
        return "user/add.html";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute User u) throws IllegalStateException, IOException {
        if(u.getFile() != null && !u.getFile().isEmpty()){
            final String UPLOAD_FOLDER = "E:/JMaster/JavaSpringBoot/project2/project2/src/main/resources/static/upload/";
            String filename = u.getFile().getOriginalFilename();
            File newFile = new File(UPLOAD_FOLDER + filename);
            u.getFile().transferTo(newFile);
            u.setAvatar(filename);//save to db
        }
        userRepo.save(u);
        return "redirect:/user/search";
    }

    @PostMapping("/upload-multi")
    public String add(@RequestParam("files") MultipartFile[] files) throws IllegalStateException, IOException {
        System.out.println(files.length);
        for (MultipartFile file : files)
            if (!file.isEmpty()) {
                final String UPLOAD_FOLDER = "E:/JMaster/JavaSpringBoot/project2/project2/src/main/resources/static/upload/";

                String filename = file.getOriginalFilename();
                File newFile = new File(UPLOAD_FOLDER + filename);

                file.transferTo(newFile);
            }

        return "redirect:/user/search";
    }

    @GetMapping("/search")
    public String search(){
        return "user/search.html";
    }


}
