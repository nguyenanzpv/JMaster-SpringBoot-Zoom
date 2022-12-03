package com.springboot.project2.service;

import com.springboot.project2.dto.PageDTO;
import com.springboot.project2.dto.UserDTO;

public interface UserService {
    public void create(UserDTO user);
    public UserDTO getById(int id);
    public PageDTO<UserDTO> searchByName(String name, int page, int size);
}
