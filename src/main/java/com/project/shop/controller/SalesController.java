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

    @PostMapping("/order")
    ResponseEntity<String> orderItem(@RequestBody SalesDto salesDto, Authentication auth){
        CustomMember user = (CustomMember) auth.getPrincipal();
        Optional<Member> member = memberService.getUser(user.getId());
        Sales sales = new Sales(salesDto.getCount(), salesDto.getItemId(), member.get());
        salesService.save(sales);
        return ResponseEntity.status(200).body("구매 완료");
    }


    @GetMapping("/order/all")
    String getOrderAll(Model model){
        List<Sales> result = salesService.findAll();
        System.out.println(result.get(0));

        //model.addAttribute("orders", result);
        return "/items/list-items";
    }
}
