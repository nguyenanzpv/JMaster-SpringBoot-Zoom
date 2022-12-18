package com.springboot.project2.service;

import com.springboot.project2.dto.GroupDTO;
import com.springboot.project2.dto.PageDTO;
import com.springboot.project2.dto.StudentDTO;

import java.util.List;

public interface GroupService {
    public void create(GroupDTO groupDTO);
    public void update(GroupDTO groupDTO);
    public void delete(int id);
    public void deleteAll(List<Integer> ids);
    public StudentDTO getById(int id);
    public PageDTO<GroupDTO> search(String name, String user, int page, int size);
}
