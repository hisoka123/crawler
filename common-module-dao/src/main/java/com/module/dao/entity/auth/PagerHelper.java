package com.module.dao.entity.auth;

import java.util.List;
/**
 * 封装分页记录的POJO类
 * @author rongxinhua
 *
 * @param <T> 范型，表示操纵的实体
 */
public class PagerHelper<T> {

    private T entities;	//对象列表
    private Integer page;//当前页数
    private Integer pageSize;//每页记录条数
    private Long totalCounts;	//总记录数
    private Integer pageCounts;

    public PagerHelper(T t,Integer page,Integer pageSize,Long totalCounts){
        this.entities = t;
        this.page =  page;
        this.pageSize = pageSize;
        this.totalCounts = totalCounts;
        this.pageCounts = (Integer.parseInt(totalCounts+"")-1)/pageSize+1;
    }

    public T getEntities() {
        return entities;
    }
    public void setEntities(T entities) {
        this.entities = entities;
    }
    public Long getTotalCounts() {
        return totalCounts;
    }
    public void setTotalCounts(Long totalCounts) {
        this.totalCounts = totalCounts;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCounts(){
        return pageCounts;
    }
}
