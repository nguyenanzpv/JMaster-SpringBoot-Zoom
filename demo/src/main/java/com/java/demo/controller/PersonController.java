package com.java.demo.controller;

import com.java.demo.entity.Laptop;
import com.java.demo.entity.Person;
import com.java.demo.repo.PersonRepo;
import com.java.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    PersonRepo personRepo; //instance of jpa repo

    //test for create bean
    @Autowired //DI
    @Qualifier("laptop2") //do co 2 bean cung kieu nen phai chi dinh dung bean nao
    Laptop l1;

    @Autowired
    PersonService personService;

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

        //lstPerson.add(p);
        personRepo.save(p); //save to db
        //chuyen huong redirect trong spring
        return "redirect:/person/list"; //list la duong dan url
    }

    @GetMapping("/list")
    public String list(Model model){
        List<Person> lstPerson = personService.getAll();
        model.addAttribute("list", lstPerson);
        return "person/list.html";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        personRepo.deleteById(id);
        return "redirect:/person/list"; //list la duong dan url
    }
}
