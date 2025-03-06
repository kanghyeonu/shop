package com.project.shop.controller;

import com.project.shop.domain.Item;
import com.project.shop.domain.Notice;
import com.project.shop.repository.ItemRepository;
import com.project.shop.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final NoticeRepository noticeRepository;

//    Lombok 쓰면 필요없음
//    @Autowired
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping("/list")
    String getItemList(Model model){
        List<Item> result = itemRepository.findAll();
        //System.out.println(result.toString());
        model.addAttribute("items", result);
        return "itemList.html";
    }

    @GetMapping("/notice")
    String getNoticeList(Model model){
        List<Notice> result = noticeRepository.findAll();
        System.out.println(result.toString());
        model.addAttribute("notices", result);
        return "noticeList.html";
    }
}
