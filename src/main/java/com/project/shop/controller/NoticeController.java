package com.project.shop.controller;

import com.project.shop.domain.Notice;
import com.project.shop.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeRepository noticeRepository;

    @GetMapping("/notices/list")
    String getNoticeList(Model model){
        List<Notice> result = noticeRepository.findAll();
        System.out.println(result.toString());
        model.addAttribute("notices", result);
        return "notices/notice-list.html";
    }
}
