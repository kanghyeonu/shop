package com.project.shop.controller;

import com.project.shop.domain.Member;
import com.project.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    String createMemberForm(){
        return "members/new-members.html";
    }

    @PostMapping("/members/new")
    String signup(@ModelAttribute MemberForm form){
        Member member = new Member();

        System.out.println(form.getNickname());
        System.out.println(form.getUsername());
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //System.out.println(encoder.encode(form.getPassword()));

        //MemberService.join(item);
        return "redirect:/items/list";
    }
}
