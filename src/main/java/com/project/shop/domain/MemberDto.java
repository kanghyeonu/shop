package com.project.shop.domain;
import lombok.Getter;


/**
 * 타입 체크 용이
 * 재사용 가능
 * 기존 DTO 클래스의 변경이 필요하다면?
 * -> FE 호환을 위해 v2 등으로 새 API 생성, extends 등
 * mapping 라이브러리
 * 자바 14 이후 부터 record 키워드도 있음
 */
@Getter // private 변수 접근을 위해 필요함
public class MemberDto {
    private String username;
    private String displayName;
    MemberDto(String username, String displayName){
        this.username = username;
        this.displayName = displayName;
    }
}
