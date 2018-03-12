package com.chens.core.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.chens.core.constants.CommonContants;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.StringUtils;
import com.chens.core.vo.BaseEntity;
import com.chens.core.vo.QueryPageEntity;
import com.chens.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * 通用增删改查-抽象方法
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/12
 */
public abstract class BaseWebController<S extends IService<T>, T extends BaseEntity<T>> extends BaseController {
    @Autowired
    protected S service;

    /**
     * 根据分页对象查询
     * @param spage
     * @return
     */
    @PostMapping("/pagelist")
    public ResponseEntity<Result> pagelist(@RequestBody QueryPageEntity<T> spage){
        Page<T> page = new Page<T>(spage.getPage().getCurrent(),spage.getPage().getSize());

        if (StringUtils.isEmpty(spage.getPage().getOrderByField())) {
            spage.getPage().setOrderByField(CommonContants.BASE_ENTITY_UPDATE_TIME);
        }
        page.setOrderByField(spage.getPage().getOrderByField());
        page.setAsc(spage.getPage().isAsc());
        EntityWrapper<T> wrapper = new EntityWrapper<T>();
        if (spage.getSearch()!=null){
            //字段解析
            Field[] fields = spage.getSearch().getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                try {
                    fields[i].setAccessible(true);
                    Object value = fields[i].get(spage.getSearch());
                    if (null != value && !value.equals("")) {
                        String fieldname = StringUtils.underscoreName(fields[i].getName());
                        wrapper.like(fieldname,value.toString());
                    }
                    fields[i].setAccessible(false);
                } catch (Exception e) {
                }
            }
        }
        return  doSuccess(service.selectPage(page,wrapper));
    }

    /**
     * 新增
     * @param t
     * @return
     */
    @PostMapping("create")
    public ResponseEntity<Result> create(@RequestBody T t) {
        if(t != null){
            t.setCreateBy(1L);//test
            t.setCreateTime(new Date());
            t.setUpdateTime(new Date());
            t.setUpdateBy(1L);//test
            return doSuccess("保存成功",service.insert(t));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 更新
     * @param t
     * @return
     */
    @PutMapping("update")
    public ResponseEntity<Result> update(@RequestBody T t) {
        if(t != null){
            t.setUpdateTime(new Date());
            t.setUpdateBy(1L);//test
            return doSuccess("保存成功",service.updateById(t));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 保存
     * @param t
     * @return
     */
    @PutMapping("/save")
    public ResponseEntity<Result> save(@RequestBody T t) {
        if(t != null){
            if(t.getId()!=null)
            {
                return update(t);
            }
            else
            {
                return create(t);
            }
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 根据id获取实体对象
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public ResponseEntity<Result> getInfo(@PathVariable Long id) {
        if(id!=null)
        {
            return doSuccess("查询成功",service.selectById(id));
        }
        else{
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        if(id!=null)
        {
            return doSuccess("删除成功",service.deleteById(id));
        }
        else{
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }
}
