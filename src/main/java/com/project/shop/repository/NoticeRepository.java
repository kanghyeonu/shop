package com.project.shop.repository;


import com.project.shop.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository <Notice, Long> {
}
