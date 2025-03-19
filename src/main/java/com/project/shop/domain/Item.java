package com.project.shop.domain;

import com.sun.nio.sctp.IllegalReceiveException;
import jakarta.persistence.*;
import lombok.ToString;
import org.springframework.stereotype.Controller;


@Entity
@ToString
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Integer price;

    // lombok의 @ToString으로 지원
//    public String toString(){
//        return this.title + this.price;
//    }
    public Item(){}

    public Item(String title, String price){
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("잘못된 상품명: 상품명은 비어 있을 수 없습니다.");
        }
        if (!isNumeric(price)) {
            throw new IllegalArgumentException("잘못된 가격: 가격은 유효한 숫자여야 합니다.");
        }
        this.title = title;
        this.price = Integer.parseInt(price);
    }

    private boolean isNumeric(String price){
        try {
            Integer.parseInt(price);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
