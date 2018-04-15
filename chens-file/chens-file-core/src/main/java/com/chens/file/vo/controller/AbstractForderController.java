package com.chens.file.vo.controller;

import com.chens.core.annotation.InsertValid;
import com.chens.core.constants.CommonConstants;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseWebController;
import com.chens.file.vo.AbstractFolder;
import com.chens.file.vo.service.IAbstractFolderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 文件标签控制器
 * @author songchunlei@qq.com
 * @create 2018/4/3
 */
public abstract class AbstractForderController<S extends IAbstractFolderService<T>, T extends AbstractFolder<T>> extends BaseWebController<S,T> {

    /**
     * 新增
     * @param t
     * @return
     */
    @Override
    @PostMapping("/create")
    public ResponseEntity<Result> create(@RequestBody @Validated(InsertValid.class) T t) {
        if(t != null){
            service.insertFolder(t);
            return doSuccess(CommonConstants.SAVE_SUCCESS,t.getId());
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }


    /**
     * 根据id获取实体对象
     * @param id
     * @return
     */
    @Override
    @GetMapping("/info/{id}")
    public ResponseEntity<Result> getInfo(@PathVariable String id) {
        if(id!=null)
        {
            return doSuccess(CommonConstants.QUERY_SUCCESS,service.selectFolderById(id));
        }
        else{
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }
}
