package com.springboot.project2.rest;

import com.springboot.project2.dto.GroupDTO;
import com.springboot.project2.dto.ResponseDTO;
import com.springboot.project2.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/group")
public class GroupRESTController {
    @Autowired
    GroupService groupService;

    @PostMapping("/new")
    public ResponseDTO<GroupDTO> add(@RequestBody @Valid GroupDTO groupDTO){
        groupService.create(groupDTO);

        return ResponseDTO.<GroupDTO>builder().status(200).data(groupDTO).build();
    }
}
