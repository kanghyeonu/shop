package com.project.shop.service;

import com.project.shop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Autowired
    MyUserDetailsService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        DB에서 username을 가진 유저를 찾아와서
//        return new User(유저아이디, 비번, 권한) 해주세요
        memberRepository.findByUsername(username).
                ifPresent(m -> );
    }

}