package com.project.shop.service;

import com.project.shop.domain.Comment;
import com.project.shop.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void saveComment(Comment comment){
        validateComment(comment);
        commentRepository.save(comment);
    }

    public List<Comment> getAllItemComments(Long parentId){return commentRepository.findByParentId(parentId);}

    void validateComment(Comment comment){
        /**
         * 뭘 검증해야하나?
         * 부적절한 표현,
         * isNUll?
         */
    }


}
