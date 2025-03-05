package com.project.shop.controller;

import com.project.shop.domain.Item;
import com.project.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

//    Lombok 쓰면 필요없음
//    @Autowired
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping("/list")
    String getItemList(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("name", "sample");
        return "itemList.html";
    }
}
