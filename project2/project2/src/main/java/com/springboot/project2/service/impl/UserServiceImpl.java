package com.springboot.project2.service.impl;

import com.springboot.project2.dto.PageDTO;
import com.springboot.project2.dto.UserDTO;
import com.springboot.project2.entity.User;
import com.springboot.project2.repo.UserRepo;
import com.springboot.project2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Override
    @Transactional
    public void create(UserDTO user) {
        User u = new ModelMapper().map(user,User.class);
        //convert dto - entity
        /*u.setName(user.getName());
        u.setUsername(user.getUsername());
        u.setBirthdate(user.getBirthdate());
        u.setPassword(user.getPassword());
        u.setAvatar(user.getAvatar());*/

        userRepo.save(u);
    }

    @Override
    public UserDTO getById(int id) {
        User user = userRepo.findById(id).orElseThrow(NoResultException::new); //java8 lambda
        /*UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setBirthdate(user.getBirthdate());
        userDto.setAvatar(user.getAvatar());
        userDto.setCreatedAt(user.getCreatedAt());*/
        UserDTO userDto = new ModelMapper().map(user,UserDTO.class);
        return userDto;
    }

    @Override
    public PageDTO<UserDTO> searchByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<User> pageRS = userRepo.searchByName("%"+name+"%", pageable);
        PageDTO<UserDTO> pageDTO = new PageDTO<>();
        pageDTO.setTotalPages(pageRS.getTotalPages());
        pageDTO.setTotalElements(pageRS.getTotalElements());

        List<UserDTO> userDTOs = new ArrayList<>();
        for(User user:pageRS.getContent()){
            userDTOs.add(new ModelMapper().map(user,UserDTO.class));
        }
        pageDTO.setContents(userDTOs); //set vao pageDto
        return pageDTO;
    }
}