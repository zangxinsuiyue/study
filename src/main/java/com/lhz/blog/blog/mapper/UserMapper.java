package com.lhz.blog.blog.mapper;

import com.lhz.blog.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 */
public interface UserMapper {
    /**
     * 根据主键删除数据
     * @param id 主键id
     * @return 返回受影响行数
     * */
    int deleteByPrimaryKey(Integer id);
    /**
     * 插入完整的数据，所有的属性都要赋值
     * @param record 封装User信息
     * @return 返回受影响行数
     * */
    int insert(User record);
    /**
     * 有选择性的插入数据，不是所有的字段都需要赋值
     * @param record 封装User信息
     * @return 返回受影响行数
     * */
    int insertSelective(User record);
    /**
     * 根据主键查询数据
     * @param id 主键id
     * @return 将返回的用户数据封装为User对象
     * */
    User selectByPrimaryKey(Integer id);
    /**
     * 有选择性地更新数据，不用所有字段都强制更新
     * @param record 封装修改过数据的User对象
     * @return 返回受影响的行数
     * */
    int updateByPrimaryKeySelective(User record);
    /**
     * 强制更新所有属性，每个字段都要更新
     * @param record 封装修改过数据的User对象
     * @return 返回受影响行数
     * */
    int updateByPrimaryKey(User record);
}