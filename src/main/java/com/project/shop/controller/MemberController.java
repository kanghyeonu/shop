package com.project.shop.controller;

import com.project.shop.domain.Member;
import com.project.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        member.setUsername(form.getUsername());
        member.setPassword(form.getPassword());
        member.setDisplayName(form.getDisplayName());
        memberService.join(member);
        return "redirect:/items/list";
    }

    @GetMapping("/login")
    String login() {
        return "/members/login.html";
    }

    @GetMapping("/my-page")
    String myPage(Authentication auth) {
        if (auth.isAuthenticated()){

        }
        return "/members/my-page.html";
    }
}
