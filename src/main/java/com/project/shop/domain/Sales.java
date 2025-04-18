package com.project.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long itemId;

    @ManyToOne()
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private Member member;

    private Integer count;
    @CreationTimestamp
    private LocalDateTime created;

    public Sales(int count, Long itemId, Member member){
        this.count = count;
        this.itemId = itemId;
        this.member = member;
    }
}
