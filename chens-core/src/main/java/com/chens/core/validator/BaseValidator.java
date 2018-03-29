package com.chens.core.validator;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.chens.core.util.AopTargetUtil;
import com.chens.core.util.ApplicationContextUtil;
import com.chens.core.util.EntityWrapperHelper;
import com.chens.core.vo.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通用校验框架
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/29
 */
public class BaseValidator <S extends IService<T>, T extends BaseEntity<T>> {

    @Autowired
    protected S service;

    /**
     * 判断是否存在，传入查询条件
     * @param t
     * @return
     */
    public boolean checkIsNotExist(T t) throws Exception {

        //根据特定字段查询是否存在
        EntityWrapper<T> wrapper = EntityWrapperHelper.getQueryEntityWrapperByEntity(t,false);
        int count = service.selectCount(wrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

}
