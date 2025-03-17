package com.project.shop.controller;

import com.project.shop.domain.Item;
import com.project.shop.domain.Notice;
import com.project.shop.repository.ItemRepository;
import com.project.shop.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

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
        return "items/item-list.html";
    }

    @GetMapping("/items/new")
    String createItemsForm(Model model){
        return "items/new-items.html";
    }

    @PostMapping("/items/new")
    String addItems(@RequestParam Map<String, String> formData){
        String title = formData.get("title");
        String price = formData.get("price");

        try{
            Item item = new Item(title, price);
            itemRepository.save(item);
        } catch (IllegalArgumentException e){
            System.out.println("유효하지 않은 상품 정보");
            return "redirect:/items/list";
        }

        return "redirect:/items/list";
    }

    @GetMapping("items/detail/{id}")
    String showDetail(@PathVariable Long id, Model model){
        Optional<Item> result = itemRepository.findById(id);
        if (result.isPresent()){
            model.addAttribute("item", result.get());
        }
        return "items/detail-items.html";
    }
}
