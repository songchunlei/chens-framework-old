package com.chens.core.web;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import com.chens.core.util.EntityWrapperHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.chens.core.constants.CommonConstants;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.GetValidateMsg;
import com.chens.core.util.StringUtils;
import com.chens.core.vo.BaseEntity;
import com.chens.core.vo.QueryPageEntity;
import com.chens.core.vo.Result;

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
            spage.getPage().setOrderByField(CommonConstants.BASE_ENTITY_UPDATE_TIME);
        }
        page.setOrderByField(spage.getPage().getOrderByField());
        page.setAsc(spage.getPage().isAsc());
        //模糊查询各字段
        EntityWrapper<T>  wrapper = EntityWrapperHelper.getQueryEntityWrapperByEntity(spage.getSearch(),true);
        return  doSuccess(service.selectPage(page,wrapper));
    }

    /**
     * 新增
     * @param t
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Result> create(@RequestBody @Validated T t,BindingResult result) {
        /*
        String msg = GetValidateMsg.handlerValidateMsg(result);
        if(StringUtils.isNotEmpty(msg))
        {
            throw new BaseException(BaseExceptionEnum.VALIDATE_NOPASS.getCode(),msg);
        }
        */
        if(t != null){
            /* 交给MyMetaObjectHandler
            t.setCreateBy(BaseContextHandler.getUserId());
            t.setCreateTime(new Date());
            t.setUpdateTime(new Date());
            t.setUpdateBy(BaseContextHandler.getUserId());
            t.setTenantId(BaseContextHandler.getTenantId());
            */
        	service.insert(t);
            return doSuccess("保存成功",t.getId());
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 更新
     * @param t
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Result> update(@RequestBody @Validated T t,BindingResult result) {
        String msg = GetValidateMsg.handlerValidateMsg(result);
        if(StringUtils.isNotEmpty(msg))
        {
            throw new BaseException(BaseExceptionEnum.VALIDATE_NOPASS.getCode(),msg);
        }
        if(t != null){
            /* 交给MyMetaObjectHandler
            t.setUpdateTime(new Date());
            t.setUpdateBy(BaseContextHandler.getUserId());
            */
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
    public ResponseEntity<Result> save(@RequestBody @Validated T t,BindingResult result) {
        if(t != null){
            if(t.getId()!=null)
            {
                return update(t,result);
            }
            else
            {
                return create(t,result);
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
    public ResponseEntity<Result> getInfo(@PathVariable String id) {
        if(id!=null)
        {
            return doSuccess("查询成功",service.selectById(id));
        }
        else{
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 列表查询
     * @param t
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Result> list(T t) {
        if(t!=null)
        {
            return doSuccess("查询成功",service.selectList(new EntityWrapper<>(t)));
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
    public ResponseEntity<Result> delete(@PathVariable String id) {
        if(StringUtils.isNotEmpty(id))
        {
            return doSuccess("删除成功",service.deleteById(id));
        }
        else{
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }
    
    
    /**
     * 批量删除
     * @param id
     * @return
     */
    @DeleteMapping("/batchDelete/{id}")
    public ResponseEntity<Result> batchDelete(@PathVariable String id) {
        if(StringUtils.isNotEmpty(id))
        {
        	String[] idArray = id.split(",");
    		List<String> idList = Arrays.asList(idArray);
            return doSuccess("删除成功",service.deleteBatchIds(idList));
        }
        else{
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }
    
    
}
