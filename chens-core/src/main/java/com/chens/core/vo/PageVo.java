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
    private int current;
    /**
     * 每页数量
     */
    private int size;
    /**
     * 排序
     */
    private String orderByField;
    /**
     * 是否升序
     */
    private boolean isAsc;

    public PageVo() {
        this.current = 1;
        this.size = 10;
        this.isAsc = true;
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
}
