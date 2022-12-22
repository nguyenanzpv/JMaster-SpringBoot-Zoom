package com.springboot.project2.rest;

import com.springboot.project2.dto.PageDTO;
import com.springboot.project2.dto.ResponseDTO;
import com.springboot.project2.dto.UserDTO;
import com.springboot.project2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

@RestController
@RequestMapping("/api/user")
public class UserRESTController {
    @Autowired
    UserService userService;

    public void add(@RequestParam("files") MultipartFile[] file) throws IOException {

    }

    @PostMapping("/new")
    public ResponseDTO<Void> add(@ModelAttribute UserDTO u) throws IOException {
        if(u.getFile() != null && !u.getFile().isEmpty()){
            final String UPLOAD_FOLDER = "E:/JMaster/JavaSpringBoot/project2/project2/src/main/resources/static/upload/";
            String filename = u.getFile().getOriginalFilename();
            File newFile = new File(UPLOAD_FOLDER + filename);
            u.getFile().transferTo(newFile);
            u.setAvatar(filename);//save to db
        }
        userService.create(u);
        /*ResponseDTO<Void> responseDTO = new ResponseDTO<Void>();
        responseDTO.setStatus(200);
        return responseDTO;*/

        //neu responerDTO co su dung @Builder thi viet nhu sau
        // @Builder tu tao cac doi tuong ResponseDTO
        return ResponseDTO.<Void>builder().status(200).build();
    }

    @GetMapping("/get/{id}")//get/10
    public ResponseDTO<UserDTO> get(@PathVariable("id") int id) {
        UserDTO userDTO = userService.getById(id);
        return ResponseDTO.<UserDTO>builder().status(200)
                .data(userDTO).build();
    }

    @DeleteMapping("/delete")
    public ResponseDTO<Void> delete(@RequestParam("id") int id) {
        userService.delete(id);
        return ResponseDTO.<Void>builder().status(200)
                .build();
    }

    @GetMapping("/search")
    public ResponseDTO<PageDTO<UserDTO>> search(
            @RequestParam(name="id", required = false) Integer id,
            @RequestParam(name ="name", required=false) String name,
            @RequestParam(name = "start", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date start,
            @RequestParam(name = "end", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date end,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "page", required = false) Integer page,
            Model model
    )
    {
        size = size == null ? 10 : size;
        page = page == null ? 0 : page;
        name = name == null ?"" : name;

        PageDTO<UserDTO> pageRS = userService.searchByName("%"+name+"%",page,size);
        return ResponseDTO.<PageDTO<UserDTO>>builder().status(200)
                .data(pageRS).build();
    }

    @PostMapping("/edit")
    public ResponseDTO<Void> edit(@ModelAttribute UserDTO userDTO) throws IllegalStateException, IOException {
        if(userDTO.getFile() != null && !userDTO.getFile().isEmpty()){
            final String UPLOAD_FOLDER = "E:/JMaster/JavaSpringBoot/project2/project2/src/main/resources/static/upload/";
            String filename = userDTO.getFile().getOriginalFilename();
            File newFile = new File(UPLOAD_FOLDER + filename);
            userDTO.getFile().transferTo(newFile);
            userDTO.setAvatar(filename);//save to db
        }
        userService.update(userDTO);
        return ResponseDTO.<Void>builder().status(200)
                .build();
    }
}
