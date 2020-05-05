package com.lhz.blog.blog.service;

import com.lhz.blog.blog.dto.PageDTO;
import com.lhz.blog.blog.dto.QuestionDTO;
import com.lhz.blog.blog.mapper.QuestionMapper;
import com.lhz.blog.blog.mapper.UserMapper;
import com.lhz.blog.blog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class PageDtoService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    public PageDTO getPage(String currentPage, String pageSize){
        PageDTO pageInfo = new PageDTO();
        //currentPage为前端传入的当前页号，但是若要用limit分页还需要将得到通过getIndex方法
        //来得到sql语句中真实的index，有前台传入的currentPage是用来进行setCurrentPage的
        Integer currentPage1 = 1;
        Integer pageSize1 = pageInfo.getPageSize();
        try {
            currentPage1 = Integer.parseInt(currentPage);
            //判断前端传入的数据是否合法（简单判断是否大于0）
            currentPage1 = currentPage1>0?currentPage1:1;
            pageSize1 = Integer.parseInt(pageSize);
            //这两个方法是用来给PageInfo对象中的currentPage，以及PageSize设置初始值，以便其他方这两个值
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        pageInfo.setTotalCount(questionMapper.getTotalCount());
        //写在这里是为了防止出现异常而无法初始化，
        //这个参数是传过来的
        pageInfo.setCurrentPage(currentPage1);
        //这个参数也是传过来的的
        pageInfo.setPageSize(pageSize1);
        //这个参数是通过bookDao方法得到的
        pageInfo.setTotalPage(pageInfo.getTotalPage());
        Integer index = pageInfo.getIndex();
        pageInfo.setIndex(index);
        pageInfo.setHasPrevious();
        pageInfo.setHasNext();
        List<QuestionDTO> questionDTOS = questionMapper.getCurrentPageQuestions(pageInfo.getIndex(),pageInfo.getPageSize());
        for (QuestionDTO questionDTO : questionDTOS) {
            User user = userMapper.selectByPrimaryKey(questionDTO.getCreator());
            questionDTO.setUser(user);
        }
        pageInfo.setPageData(questionDTOS);
        return pageInfo;
    }

    public PageDTO getOwnPage(Long creator, String currentPage, String pageSize){
        PageDTO ownPageInfo = new PageDTO();
        //currentPage为前端传入的当前页号，但是若要用limit分页还需要将得到通过getIndex方法
        //来得到sql语句中真实的index，有前台传入的currentPage是用来进行setCurrentPage的
        Integer currentPage1 = 1;
        Integer pageSize1 = ownPageInfo.getPageSize();
        try {
            currentPage1 = Integer.parseInt(currentPage);
            //判断前端传入的数据是否合法（简单判断是否大于0）
            currentPage1 = currentPage1>0?currentPage1:1;
            pageSize1 = Integer.parseInt(pageSize);
            //这两个方法是用来给PageInfo对象中的currentPage，以及PageSize设置初始值，以便其他方这两个值
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        ownPageInfo.setTotalCount(questionMapper.getOwnTotalCount(creator));
        //写在这里是为了防止出现异常而无法初始化，
        //这个参数是传过来的
        ownPageInfo.setCurrentPage(currentPage1);
        //这个参数也是传过来的的
        ownPageInfo.setPageSize(pageSize1);
        //这个参数是通过bookDao方法得到的
        ownPageInfo.setTotalPage(ownPageInfo.getTotalPage());
        Integer index = ownPageInfo.getIndex();
        ownPageInfo.setIndex(index);
        ownPageInfo.setHasPrevious();
        ownPageInfo.setHasNext();
        List<QuestionDTO> questionDTOS = questionMapper.getCurrentUserQuestions(creator,ownPageInfo.getIndex(),ownPageInfo.getPageSize());
        for (QuestionDTO questionDTO : questionDTOS) {
            User user = userMapper.selectByPrimaryKey(questionDTO.getCreator());
            questionDTO.setUser(user);
        }
        ownPageInfo.setPageData(questionDTOS);
        return ownPageInfo;
    }
}
