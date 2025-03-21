package com.project.shop.controller;

import com.project.shop.domain.Item;
import com.project.shop.domain.Notice;
import com.project.shop.repository.ItemRepository;
import com.project.shop.repository.NoticeRepository;
import com.project.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

//    Lombok 쓰면 필요없음
//    @Autowired
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping("/items/list")
    String getItemList(Model model){
        List<Item> result = itemRepository.findAll();
        //System.out.println(result.toString());
        model.addAttribute("items", result);
        return "items/list-items.html";
    }

    @GetMapping("/items/new")
    String createItemsForm(){
        return "items/new-items.html";
    }

    @PostMapping("/items/new")
    String addItems(@RequestParam Map<String, String> form){
        Item item = new Item(form.get("title"), form.get("price"));
        itemService.saveItem(item);
        return "redirect:/items/list";
    }

    @GetMapping("items/detail/{id}")
    String showDetail(@PathVariable Long id, Model model){
        Optional<Item> result = itemService.getItem(id);
        if (result.isPresent()){
            model.addAttribute("item", result.get());
            return "items/detail-items.html";
        }
        else{
            return "redirect:/items/list";
        }
    }

    @GetMapping("/items/update/{id}")
    String updateItemForm(@PathVariable Long id, Model model){
        Optional<Item> result = itemService.getItem(id);
        if (result.isPresent()){
            model.addAttribute("item", result.get());
            return "items/update-item.html";
        }
        else{
            return "redirect:/items/list";
        }
    }

    @PostMapping("/items/update")
    String updateItem(@RequestParam Long id, @RequestParam String title, @RequestParam String price){
        itemService.updateItem(id, title, price);
        return "redirect:/items/list";
    }
}
