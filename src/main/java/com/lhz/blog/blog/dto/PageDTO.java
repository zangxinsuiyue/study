package com.lhz.blog.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 此类是将分页功能抽象出来的一个类,类中的字段代表着此分页的每个功能
 * @author Administrator
 */
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {
    /**
     * 当前所在页数
     * */
    private Integer currentPage;
    /**
     * 总页数
     * */
    private Integer totalPage;
    /**
     * 总记录数量
     * */
    private Integer totalCount;
    /**
     * 每页显示条数
     * */
    private Integer pageSize=5;
    /**
     * limit的开始索引
     * */
    private Integer index;
    /**
     * 是否有下一页
     * */
    private Boolean hasNext;
    /**
     * 是否有上一页
     * */
    private Boolean hasPrevious;
    /**
     * 将一页数据封装起来
     * */
    private List<QuestionDTO> pageData;


    public void setTotalPage(Integer totalPage){
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        currentPage = currentPage > 0 ? currentPage:1;
        this.currentPage =  currentPage > getTotalPage()?getTotalPage():currentPage;
    }

    public Integer getTotalPage() {
        //总页数 = 总记录数/每页记录数,若总记录数%每页记录数==0,则说明刚好
        return getTotalCount()%getPageSize() == 0 ? getTotalCount()/getPageSize() : getTotalCount()/getPageSize()+1;
    }

    public Integer getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }
    public void setIndex(Integer index){
        this.index = index;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getIndex() {
        //索引 = (页号-1)*页大小
        Integer i = (getCurrentPage()-1)*getPageSize();
        if (i<0){i=0;}
        return i;
    }
    public void setHasPrevious(){
        this.hasPrevious = isHasPrevious();
    }

    public void setHasNext(){
        this.hasNext = isHasNext();
    }
    public Boolean isHasNext() {
        return getCurrentPage() < getTotalPage();
    }


    public Boolean isHasPrevious() {
        return getCurrentPage() > 1;
    }

    public List<QuestionDTO> getPageData() {
        return pageData;
    }

    public void setPageData(List pageData) {
        this.pageData = pageData;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", index=" + index +
                ", hasNext=" + hasNext +
                ", hasPrevious=" + hasPrevious +
                ", pageData=" + pageData +
                '}';
    }

}
