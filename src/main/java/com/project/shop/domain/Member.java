package com.project.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String displayName;

    @ToString.Exclude
    @OneToMany(mappedBy = "member") // Sales 테이블에서 member를 외래키로 쓰고 있는 칼럼 이름
    private List<Sales> sales = new ArrayList<>();

}
