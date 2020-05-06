package com.lhz.blog.blog.service;

import com.lhz.blog.blog.enums.CommentType;
import com.lhz.blog.blog.exception.CustomException;
import com.lhz.blog.blog.mapper.CommentMapper;
import com.lhz.blog.blog.mapper.QuestionMapper;
import com.lhz.blog.blog.message.impl.CommentErrorMessage;
import com.lhz.blog.blog.pojo.Comment;
import com.lhz.blog.blog.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    private Integer effectedCount;
    @Transactional(rollbackFor = Exception.class)
    public int insert(Comment comment){
        //每个评论都是针对某个问题或者上一级评论的，若是上一级为空，则说明此评论并不是针对某个问题或评论来恢复的
        if(comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomException(CommentErrorMessage.TARGET_NOT_FOUNT);
        }
        //判断自身是几级回复的，通过将传入的等级与枚举中的等级一一对比，若有相等的则返回true，否则返回false;
        if(comment.getType() == null || !CommentType.isExist(comment.getType())){
            throw new CustomException(CommentErrorMessage.REPLY_OBJECT_DOES_NOT_EXIST);
        }
        //如果评论等级是一级评论则说明此时评论是回复问题的
        if(comment.getType().equals(CommentType.LEVEL_ONE_COMMENT.getLevel())){
            //如果是回复问题的，就将他的目标问题查出来
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question == null){
                throw new CustomException(CommentErrorMessage.REPLY_QUESTION_DOES_NOT_EXIST);
            }else {
                effectedCount = commentMapper.insert(comment);
                question.setCommentCount(1);
                questionMapper.updateCommentCount(question);
            }
        }else {
            //如果是回复评论的，就将他评论查出来
            Comment comment1 = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(comment1 == null){
                throw new CustomException(CommentErrorMessage.REPLY_COMMENT_DOES_NOT_EXIST);
            }else {
                effectedCount = commentMapper.insert(comment);
            }
        }
        return effectedCount;
    }
}
