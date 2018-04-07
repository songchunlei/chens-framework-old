package com.chens.bpm.service;

import com.baomidou.mybatisplus.service.IService;
import com.chens.bpm.vo.WorkFlowRequestParam;

/**
 * 流程引擎服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/30
 */
public interface IWfBaseService<T>  extends IService<T> {

    /**
     * 创建草稿
     * @param workFlowRequestParam
     * @return
     */
    String createDraft(WorkFlowRequestParam<T> workFlowRequestParam);

    /**
     * 发起流程
     * @param workFlowRequestParam
     * @return
     */
    boolean submitDraft(WorkFlowRequestParam<T> workFlowRequestParam);

    /**
     * 提交办理（只读审批）
     * @param workFlowRequestParam
     * @return
     */
    boolean pass(WorkFlowRequestParam<T> workFlowRequestParam);

    /**
     * 提交办理（非只读审批）
     * @param workFlowRequestParam
     * @return
     */
    boolean passWithEdit(WorkFlowRequestParam<T> workFlowRequestParam);

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
