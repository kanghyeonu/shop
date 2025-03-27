package com.project.shop.service;

import com.project.shop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
}
