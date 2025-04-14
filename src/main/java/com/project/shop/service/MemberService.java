package com.project.shop.service;

import com.project.shop.domain.Member;
import com.project.shop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder){
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void join(Member member){
        validateMember(member);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        if (member.getDisplayName().isEmpty()){
            member.setDisplayName(member.getUsername());
        }
        memberRepository.save(member);
    }

    private void validateMember(Member member){
        validateUsername(member.getUsername());
        validatePassword(member.getPassword());
        validateDisplayName(member.getDisplayName());
    }
    private void validateUsername(String username){
        if (username.length() > 15){
            throw new IllegalArgumentException("사용자 아이디는 15이하여야합니다.");
        }

        if (username.matches("-?\\d+(\\.\\d+)?")){
            throw new IllegalArgumentException("사용자 아이디는 1글자 이상의 문자가 포함되어야 합니다");
        }

        memberRepository.findByUsername(username).
                ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    private void validatePassword(String password){
        // 정규식으로 나타내는게 정배일 듯
        // 지금은 간략화
        if (password.length() < 4){
            throw new IllegalArgumentException("비밀번호는 4글자 이상이어야 합니다");
        }

    }

    private void validateDisplayName(String displayName){
        if (displayName.isEmpty() || displayName.trim().length() == 0){
            return;
        }

        memberRepository.findByDisplayName(displayName).
                ifPresent(m -> {
                    throw new IllegalStateException("사용중인 닉네임입니다.");
                });
    }

}
