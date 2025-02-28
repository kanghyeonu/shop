package com.project.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    @GetMapping("/list")
    String getItemList(Model model){
        model.addAttribute("name", "sample");
        return "itemList.html";
    }
}
