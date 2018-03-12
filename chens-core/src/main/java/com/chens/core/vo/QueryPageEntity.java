package com.chens.core.vo;

import com.baomidou.mybatisplus.plugins.Page;

import java.io.Serializable;

/**
 * 分页查询
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/12
 */
public class QueryPageEntity<T> implements Serializable {

    private static final long serialVersionUID = 2280382250877953228L;

    private PageVo page;

    private T search;

    public T getSearch() {
        return search;
    }

    public void setSearch(T search) {
        this.search = search;
    }



    public QueryPageEntity() {
    }

    public QueryPageEntity(PageVo page, T search) {
        this.page = page;
        this.search = search;
    }

    public PageVo getPage() {
        return page;
    }

    public void setPage(PageVo page) {
        this.page = page;
    }
}
