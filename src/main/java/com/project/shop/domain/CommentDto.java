package com.project.shop.domain;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDto {
    private String comment; // 댓글 내용
    private Long parentId; // 댓글 달린 상품
    private String username; // 댓글 쓴 사람
}
