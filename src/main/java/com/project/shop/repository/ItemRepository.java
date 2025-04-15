package com.project.shop.repository;

import com.project.shop.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// 데이터 접근을 위한 클래스 = repository
// CRUD 설계 및 구현
public interface ItemRepository extends JpaRepository <Item, Long>{
    Page<Item> findPageBy(Pageable pageable);
    Page<Item> findAllByTitleContains(String keyword, Pageable pageable); // index 안씀

}
