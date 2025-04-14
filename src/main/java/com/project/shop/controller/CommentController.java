package com.project.shop.controller;

import com.project.shop.domain.Comment;
import com.project.shop.domain.CommentDto;
import com.project.shop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/new")
    ResponseEntity<String> addComment(@RequestBody CommentDto commentDto){
        Comment comment = new Comment(commentDto);
        commentService.saveComment(comment);
        return ResponseEntity.status(200).body("댓글 쓰기 완료");
    }

}
