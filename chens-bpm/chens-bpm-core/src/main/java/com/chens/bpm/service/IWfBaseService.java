package com.chens.bpm.service;

import com.baomidou.mybatisplus.service.IService;

/**
 * 流程引擎服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/30
 */
public interface IWfBaseService<T>  extends IService<T> {

    /**
     * 创建草稿
     * @param t
     * @return
     */
    String createDraft(T t);

    /**
     * 提交
     * @param t
     * @return
     */
    boolean submitDraft(T t);

    /**
     * 审批通过
     * @param t
     * @return
     */
    boolean pass(T t);

    /**
     * 审批不通过
     * @param t
     * @return
     */
    boolean noPass(T t);

    /**
     * 发布
     */
    boolean publish(T t);

    /**
     * 取消发布
     */
    boolean unPublish(T t);



}
