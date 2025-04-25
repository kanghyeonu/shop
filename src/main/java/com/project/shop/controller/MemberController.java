package com.project.shop.controller;

import com.project.shop.domain.Member;
import com.project.shop.service.MemberService;
import com.project.shop.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

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

    @PostMapping("/login/jwt")
    @ResponseBody
    String loginJWT(@RequestBody Map<String, String> data, HttpServletResponse response){
        System.out.println("JWT 로그인");
        var authToken = new UsernamePasswordAuthenticationToken(
                data.get("username"), data.get("password")
        );
        var auth = authenticationManagerBuilder.getObject().authenticate(authToken);
        // 로그인 후의 파라미터의 Authentication auth 변수를 세팅
        SecurityContextHolder.getContext().setAuthentication(auth);

        var jwt = JwtUtil.createToken(SecurityContextHolder.getContext().getAuthentication());
        System.out.println(jwt);

        var cookie = new Cookie("jwt", jwt);
        cookie.setMaxAge(10); // 쿠키 유효기간, jwt랑 비슷하게 해주면 될듯
        cookie.setHttpOnly(true); // js에서 조정하기 어렵게
        cookie.setPath("/"); // 쿠키의 요쳥 경로
        response.addCookie(cookie);

        return jwt;
    }

    @GetMapping("/my-page/jwt")
    @ResponseBody
    String mypageJWT(){
        return "";
    }
}
