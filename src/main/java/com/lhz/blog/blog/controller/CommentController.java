package com.lhz.blog.blog.controller;

import com.lhz.blog.blog.dto.CommentDTO;
import com.lhz.blog.blog.dto.ResultDTO;
import com.lhz.blog.blog.mapper.CommentMapper;
import com.lhz.blog.blog.message.impl.CommentErrorMessage;
import com.lhz.blog.blog.pojo.Comment;
import com.lhz.blog.blog.pojo.User;
import com.lhz.blog.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Object comment(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CommentErrorMessage.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(26);
        comment.setLikeCount(1L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
