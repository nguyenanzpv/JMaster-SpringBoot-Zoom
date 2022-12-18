package com.springboot.project2.service.impl;

import com.springboot.project2.dto.GroupDTO;
import com.springboot.project2.dto.PageDTO;
import com.springboot.project2.dto.StudentDTO;
import com.springboot.project2.dto.UserDTO;
import com.springboot.project2.entity.Group;
import com.springboot.project2.entity.User;
import com.springboot.project2.repo.GroupRepo;
import com.springboot.project2.repo.UserRepo;
import com.springboot.project2.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepo groupRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    @Transactional
    public void create(GroupDTO groupDTO) {
        Group group = new Group();
        group.setName(groupDTO.getName());

        List<User> users = new ArrayList<>();

        for(UserDTO userDTO: groupDTO.getUsers()){
            User user = userRepo.findById(userDTO.getId()).orElseThrow(NoResultException::new);
            users.add(user);
        }
        group.setUsers(users);
        groupRepo.save(group);
    }

    @Override
    public void update(GroupDTO groupDTO) {
        Group group = groupRepo.findById(groupDTO.getId()).orElseThrow(NoResultException::new);
        group.setName(groupDTO.getName());

        //neu da co du lieu bang trung gian (user_group)
        if (group.getUsers() != null) {
            group.getUsers().clear();

            //List<User> users = group.getUsers();
            for (UserDTO userDTO : groupDTO.getUsers()) {
                User user = userRepo.findById(userDTO.getId()).orElseThrow(NoResultException::new);
                group.getUsers().add(user);
            }
        }else{ //them moi
            List<User> users = new ArrayList<>();

            for(UserDTO userDTO: groupDTO.getUsers()){
                User user = userRepo.findById(userDTO.getId()).orElseThrow(NoResultException::new);
                users.add(user);
            }
            group.setUsers(users);
        }
        groupRepo.save(group);

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll(List<Integer> ids) {

    }

    @Override
    public StudentDTO getById(int id) {
        return null;
    }

    @Override
    public PageDTO<GroupDTO> search(String name, String user, int page, int size) {
        return null;
    }
}
