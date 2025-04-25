package com.project.shop.util;

import com.project.shop.domain.CustomMember;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            filterChain.doFilter(request, response); // 다음 필터 실행
            return;
        }
        var jwtCookie = "";
        for (int i = 0; i < cookies.length; i++){
            if (cookies[i].getName().equals("jwt")){
                jwtCookie = cookies[i].getValue();
            }
        }
        System.out.println(jwtCookie);

        Claims claim;
        try{
            claim = JwtUtil.extractToken(jwtCookie);
        } catch (Exception e){
            filterChain.doFilter(request, response);
            return;
        }
        var arr = claim.get("authorities").toString().split(",");
        var authorities = Arrays.stream(arr).map(a -> new SimpleGrantedAuthority(a)).toList();

        var customUser = new CustomMember(
                claim.get("username").toString(),
                "none",
                authorities
        );

        var authToken = new UsernamePasswordAuthenticationToken(
                customUser,
                null,
                authorities
        );
        // 위의 claim 요소들의 이름 패스워드가 auth.getPrincipal()에 추가됨
        // 만든 CustomUser()을 넣어도 됨
        authToken.setDetails(new WebAuthenticationDetailsSource()
                .buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);

        //요청들어올때마다 실행할코드~~
        filterChain.doFilter(request, response);
    }

}
