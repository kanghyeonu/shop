package com.project.shop.util;

import com.project.shop.domain.CustomMember;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    // 털리면 큰 일남
    // applications 프로퍼티에 빼는게 좋을 듯
    static final SecretKey key =
            Keys.hmacShaKeyFor(Decoders.BASE64.decode(
                    "jwtpassword123jwtpassword123jwtpassword123jwtpassword123jwtpassword"
            ));

    // JWT 만들어주는 함수
    public static String createToken(Authentication auth) {
        var user = (CustomMember) auth.getPrincipal();
        var authorities = auth.getAuthorities().stream().map(a -> a.toString())
                .collect(Collectors.joining(","));
        // 유저의 권한(리스트)을 문자열 형태로 이어 뿥임
        String jwt = Jwts.builder()
                .claim("username", user.getUsername())// 민감한 내용은 제외하시오
                .claim("authorities", authorities)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 10000)) //유효기간 10초
                .signWith(key)
                .compact();
        return jwt;
    }

    // JWT 까주는 함수
    public static Claims extractToken(String token) {
        Claims claims = Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token).getPayload();
        return claims;
    }
}
