package com.project.shop.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class SalesDto {
    private int count;
    private String username;
    private Long itemId;

    SalesDto(int count, String username, Long itemId){
        this.count = count;
        this.username = username;
        this.itemId = itemId;
    }
}
