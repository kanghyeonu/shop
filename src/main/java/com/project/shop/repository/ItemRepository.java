package com.project.shop.repository;

import com.project.shop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

// 데이터 접근을 위한 클래스 = repository
// CRUD 설계 및 구현
public interface ItemRepository extends JpaRepository <Item, Long>{
}
