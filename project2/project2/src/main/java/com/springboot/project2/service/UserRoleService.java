package com.springboot.project2.service;

import com.springboot.project2.dto.PageDTO;
import com.springboot.project2.dto.UserDTO;
import com.springboot.project2.dto.UserRoleDTO;

public interface UserRoleService {
    public void create(UserRoleDTO userRoleDTO);
    public void update(UserRoleDTO userRoleDTO);
    public void delete(int id);
    public void deleteByUserId(int userId);
    public UserRoleDTO getById(int id);
    public PageDTO<UserRoleDTO> searchByUserId(int userId, int page, int size);
    public PageDTO<UserRoleDTO> searchByRole(String role, int page, int size);
}
