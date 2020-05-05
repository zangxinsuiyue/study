package com.lhz.blog.blog.service;

import com.lhz.blog.blog.exception.CustomException;
import com.lhz.blog.blog.mapper.CommentMapper;
import com.lhz.blog.blog.message.impl.CommentErrorMessage;
import com.lhz.blog.blog.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    public int insert(Comment comment){
        if(comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomException(CommentErrorMessage.TARGET_NOT_FOUNT);
        }
       return commentMapper.insert(comment);
    }
}
