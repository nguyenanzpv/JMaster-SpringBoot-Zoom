package com.java.demo.controller;

import com.java.demo.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {
    List<Person> lstPerson = new ArrayList<Person>();
    @GetMapping("/create")
    public String create(){
        return "person/create-person.html";
    }

    @PostMapping("/create")
    public String create(@RequestParam("id") int id,@RequestParam("name") String name,@RequestParam("age") int age){
        Person p = new Person();
        p.setId(id);
        p.setName(name);
        p.setAge(age);

        lstPerson.add(p);
        //chuyen huong redirect trong spring
        return "redirect:/person/list"; //list la duong dan url
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("list", lstPerson);
        return "person/list.html";
    }
}
