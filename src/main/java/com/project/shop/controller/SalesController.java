package com.project.shop.controller;

import com.project.shop.domain.CustomMember;
import com.project.shop.domain.Member;
import com.project.shop.domain.Sales;
import com.project.shop.domain.SalesDto;
import com.project.shop.repository.MemberRepository;
import com.project.shop.service.MemberService;
import com.project.shop.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/order")
    ResponseEntity<String> orderItem(@RequestBody SalesDto salesDto, Authentication auth){
        CustomMember user = (CustomMember) auth.getPrincipal(); // myUserDetailService에서 오버라이딩으로 user id 값 세팅
        Optional<Member> member = memberService.findUserById(user.getId());
        Sales sales = new Sales(salesDto.getCount(), salesDto.getItemId(), member.get());
        salesService.save(sales);
        return ResponseEntity.status(200).body("구매 완료");
    }


    @GetMapping("/order/all")
    String getOrderAll(Model model){
        //List<Sales> result = salesService.findAll();
        // 결과를 SalesDto에 result의 값들을 Dto에 맞게 세팅 후 반환

        //Test
        var result = memberRepository.findById(1l);
        System.out.println(result.get().getSales());

        //model.addAttribute("orders", result);
        return "/items/list-items";
    }
}
