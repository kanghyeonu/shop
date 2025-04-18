package com.project.shop.controller;

import com.project.shop.domain.*;
import com.project.shop.repository.ItemRepository;
import com.project.shop.repository.NoticeRepository;
import com.project.shop.service.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final S3Service s3Service;
    private final MemberService memberService;
    private final CommentService commentService;
    private final SalesService salesService;

//    Lombok 쓰면 필요없음
//    @Autowired
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping("/items/list")
    String getItemList(){
        //List<Item> result = itemService.getAllItem();
        //System.out.println(result.toString());
        //model.addAttribute("items", result);
        return "redirect:/items/list/1";
    }

    @GetMapping("/items/list/{page}")
    String getItemPage(@PathVariable Integer page, Model model){
        Page<Item> result = null;
        if (page < 0){
            result = itemService.getItemPage(0);
        } else{
            result = itemService.getItemPage(page-1);
        }
        model.addAttribute("items", result);
        model.addAttribute("totalPages", result.getTotalPages());
        //model.addAttribute("requestPage", page);
        return "items/list-items.html";
    }

    @GetMapping("/items/new")
    String createItemsForm(){
        return "items/new-items.html";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/items/new")
    String addItems(@RequestParam Map<String, String> form, Authentication auth){
        Item item = new Item(form.get("title"), form.get("price"));
        item.setUsername(auth.getName());
        itemService.saveItem(item);
        return "redirect:/items/list";
    }

    @GetMapping("items/detail/{id}")
    String showDetail(@PathVariable Long id, Model model, Authentication auth){
        Optional<Item> result = itemService.getItem(id);
        if (result.isPresent()){
            model.addAttribute("item", result.get());
            List<Comment> comments = commentService.getAllItemComments(id);
            if (!comments.isEmpty()){
                model.addAttribute("comments", comments);
            }
            if (auth != null){
                model.addAttribute("username", auth.getName());
            }
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

    @DeleteMapping("/items")
    ResponseEntity<String> deleteItem(@RequestParam Long id){
        itemService.deleteItem(id);
        return ResponseEntity.status(200).body("");
    }

    @GetMapping("/presigned-url")
    @ResponseBody
    String getURL(@RequestParam String filename){
        String result = s3Service.createPresignedUrl("test/" + filename);
        System.out.println(result);
        return result;
    }

    @GetMapping("/items/search")
    String searchItem(@RequestParam String searchText, Model model){
        Page<Item> result = itemService.searchItem(searchText, 0);
        model.addAttribute("items", result.isEmpty() ? null : result);
        model.addAttribute("totalPages", result.getTotalPages());
        return "items/search-items";
    }


}
