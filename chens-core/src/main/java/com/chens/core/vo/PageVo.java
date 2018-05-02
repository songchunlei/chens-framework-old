package com.chens.core.vo;

import com.chens.core.constants.CommonConstants;

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
     * 排序(如 UPDATE_TIME DESC,IS_TOP ASC)
     */
    private String orderByField = CommonConstants.BASE_ENTITY_UPDATE_TIME + " DESC";

    /**
     * 查询类型（是并联查询AND还是串联查询OR）
     */
    private boolean isAnd=false;

    /**
     * like查询（是否是like查询）
     */
    private boolean isLike=true;

    /**
     * 是否只查自己
     */
    private boolean isMy = false;

    /**
     * 是否查当前租户(当租户id没有被系统接管时，则使用该字段，详见CommonConstants.NO_TENANT_TABLENAME非租户表)
     */
    private boolean isTenant = false;

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

    public boolean isAnd() {
        return isAnd;
    }

    public void setAnd(boolean and) {
        isAnd = and;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public boolean isMy() {
        return isMy;
    }

    public void setMy(boolean my) {
        isMy = my;
    }

    public boolean isTenant() {
        return isTenant;
    }

    public void setTenant(boolean tenant) {
        isTenant = tenant;
    }
}
