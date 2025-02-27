package com.project.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class
BasicController {

    // main page
    @GetMapping("/")
    String home(){
        return "home.html";
    }

    @GetMapping("/about")
    @ResponseBody
    String about(){
        return "this site is about blah~";
    }

}
