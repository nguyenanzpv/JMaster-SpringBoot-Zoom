package com.java.demo.controller;

import com.java.demo.entity.Laptop;
import com.java.demo.entity.Person;
import com.java.demo.repo.PersonRepo;
import com.java.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search")
    public String search(@RequestParam("min") int min,
                         @RequestParam("max") int max,
                         @RequestParam("page") int page,
                         @RequestParam("size") int size,
                         Model model){
        Page<Person> pagePerson = personRepo.search(min,max, PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"age")));
        System.out.println(pagePerson.getTotalPages());
        System.out.println(pagePerson.getTotalElements());
        model.addAttribute("list", pagePerson.getContent());
        return "person/list.html";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        personRepo.deleteById(id);
        return "redirect:/person/list"; //list la duong dan url
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        Person p = personRepo.findById(id).orElse(null);
        model.addAttribute("person",p);
        return "person/edit-person.html";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Person p){
        personRepo.save(p);
        return "redirect:/person/list";
    }
}
