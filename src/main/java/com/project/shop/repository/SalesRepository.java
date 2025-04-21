package com.project.shop.repository;

import com.project.shop.domain.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    // 문제 1 N+1 problem 해결 JPQL -> 별칭 강제함
    // 문제 2 JOIN된 Member 테이블의 패스워드 같은 민감한 정보까지 전부 다 나옴 -> Saels DTO 형태로
    @Query(value = "SELECT FROM Sales s JOIN FETCH s.member")
    List<Sales> customFindAll();

}
