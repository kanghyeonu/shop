package com.project.shop.service;

import com.project.shop.domain.Member;
import com.project.shop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Member> result = memberRepository.findByUsername(username);
        if (!result.isPresent()){
            throw new UsernameNotFoundException("존재하지 않는 아이디입니다. 다시 확인해보십쇼");
        }
        Member user = result.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(user.getUsername(), user.getPassword(), authorities); // User(id, 비밀번호, 권한)

    }

}