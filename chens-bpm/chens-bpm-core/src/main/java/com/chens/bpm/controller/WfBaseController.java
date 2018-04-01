package com.chens.bpm.controller;

import com.chens.bpm.service.IWfBaseService;
import com.chens.bpm.vo.WfBaseEntity;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseWebController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 流程抽象方法
 *
 * @auther songchunlei@qq.com
 * @create 2018/4/1
 */
public abstract class WfBaseController<S extends IWfBaseService<T>, T extends WfBaseEntity<T>>  extends BaseWebController<S,T> {


    protected String WF_DEF_KEY = "";

    /**
     * 自定义初始化
     */
    protected abstract void init();


    /**
     * 初始化参数
     * @param t
     */
    private void doInit(T t)
    {
        init();
        t.setWfDefineKey(WF_DEF_KEY);
    }

    /**
     * 创建草稿
     * @param t
     * @return
     */
    @PostMapping("/createDraft")
    public ResponseEntity<Result> create(@RequestBody @Validated T t) {
        if(t != null){
            this.doInit(t);
            service.createDraft(t);
            return doSuccess(t.getId());
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 提交
     * @param t
     * @return
     */
    @PostMapping("/submitDraft")
    public ResponseEntity<Result> submitDraft(@RequestBody @Validated T t) {
        if(t != null){
            this.doInit(t);
            return doSuccess(service.submitDraft(t));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 审批通过
     * @param t
     * @return
     */
    @PostMapping("/pass")
    public ResponseEntity<Result> pass(@RequestBody @Validated T t) {
        if(t != null){
            return doSuccess(service.pass(t));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }


    /**
     * 审批不通过
     * @param t
     * @return
     */
    @PostMapping("/noPass")
    public ResponseEntity<Result> noPass(@RequestBody @Validated T t) {
        if(t != null){
            return doSuccess(service.noPass(t));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 发布
     * @param t
     * @return
     */
    @PostMapping("/publish")
    public ResponseEntity<Result> publish(@RequestBody @Validated T t) {
        if(t != null){
            return doSuccess(service.publish(t));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 取消发布
     * @param t
     * @return
     */
    @PostMapping("/unPublish")
    public ResponseEntity<Result> unPublish(@RequestBody @Validated T t) {
        if(t != null){
            return doSuccess(service.unPublish(t));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }


}
