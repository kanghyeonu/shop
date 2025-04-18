package com.project.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itemId;
    @Column(nullable = false) private String username;
    private Integer count;
    @CreationTimestamp
    private LocalDateTime created;

    public Sales(SalesDto salesDto){
        this.count = salesDto.getCount();
        this.itemId = salesDto.getItemId();
        this.username = salesDto.getUsername();
    }

}
