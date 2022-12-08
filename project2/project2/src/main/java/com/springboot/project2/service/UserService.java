package com.springboot.project2.service;

import com.springboot.project2.dto.PageDTO;
import com.springboot.project2.dto.UserDTO;

import java.util.List;

public interface UserService {
    public void create(UserDTO userDTO);
    public void update(UserDTO userDTO);
    public void updatePassword(UserDTO userDTO);
    public void delete(int id);
    public void deleteAll(List<Integer> ids);
    public UserDTO getById(int id);
    public PageDTO<UserDTO> searchByName(String name, int page, int size);
}
