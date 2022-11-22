package com.java.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello-spring")
    public String hello(Model model){
        model.addAttribute("msg","Hello Spring");
        return "hi.html";
    }
}
