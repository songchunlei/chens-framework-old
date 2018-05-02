package com.chens.core.web;

import java.util.Arrays;
import java.util.List;

import com.chens.core.annotation.InsertValid;
import com.chens.core.annotation.UpdateValid;
import com.chens.core.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.chens.core.constants.CommonConstants;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.EntityWrapperHelper;
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
        //获取页面参数
        PageVo pageVo = spage.getPage();
        //创建查询条件
        Page<T> page = this.createPage(spage);
        //模糊查询各字段
        EntityWrapper<T>  wrapper = EntityWrapperHelper.getQueryEntityWrapperByEntity(spage.getSearch(),pageVo);
        return  doSuccess(CommonConstants.QUERY_SUCCESS,service.selectPage(page,wrapper));
    }

    /**
     * 新增
     * @param t
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Result> create(@RequestBody @Validated(InsertValid.class) T t) {
        if(t != null){
        	service.insert(t);
            return doSuccess(CommonConstants.SAVE_SUCCESS,t.getId());
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
    public ResponseEntity<Result> update(@RequestBody @Validated(UpdateValid.class) T t) {
        if(t != null ){
            return doSuccess(CommonConstants.SAVE_SUCCESS,service.updateById(t));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 保存
     * @param t
     * @return
     */
    @PostMapping("/save")
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
    public ResponseEntity<Result> getInfo(@PathVariable String id) {
        if(id!=null)
        {
            return doSuccess(CommonConstants.QUERY_SUCCESS,service.selectById(id));
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
            return doSuccess(CommonConstants.QUERY_SUCCESS,service.selectList(new EntityWrapper<>(t)));
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
            return doSuccess(CommonConstants.DELETE_SUCCESS,service.deleteById(id));
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
            return doSuccess(CommonConstants.SAVE_SUCCESS,service.deleteBatchIds(idList));
        }
        else{
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }
    
    
}
