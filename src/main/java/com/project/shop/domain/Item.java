package com.project.shop.domain;

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

    public boolean setPrice(String price) {
        Integer p;
        try {
            p = Integer.valueOf(price);
        } catch (NumberFormatException e) {
            return false;
        }
        this.price = p;
        return true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
