package com.chens.core.vo;

import com.baomidou.mybatisplus.plugins.Page;

import java.io.Serializable;

/**
 * 分页参数
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/12
 */
public class PageVo implements Serializable{
    private static final long serialVersionUID = 600057392108174312L;
    /**
     * 当前页数
     */
    private int current=1;
    /**
     * 每页数量
     */
    private int size=10;
    /**
     * 排序
     */
    private String orderByField;
    /**
     * 是否升序
     */
    private boolean isAsc=true;

    /**
     * 查询类型（是并联查询AND还是串联查询OR）
     */
    private boolean isAnd=false;

    public PageVo() {

    }

    public PageVo(int current,int size) {
        this.current = current;
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(String orderByField) {
        this.orderByField = orderByField;
    }

    public boolean isAsc() {
        return isAsc;
    }

    public void setAsc(boolean asc) {
        isAsc = asc;
    }

    public boolean isAnd() {
        return isAnd;
    }

    public void setAnd(boolean and) {
        isAnd = and;
    }
}
