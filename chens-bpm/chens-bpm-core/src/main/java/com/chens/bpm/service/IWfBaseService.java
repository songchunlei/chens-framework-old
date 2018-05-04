package com.chens.bpm.service;

import com.baomidou.mybatisplus.service.IService;
import com.chens.bpm.vo.PassWfVo;
import com.chens.bpm.vo.StartWfVo;
import com.chens.bpm.vo.WorkFlowRequestParam;

/**
 * 流程引擎服务
 *
 * @author songchunlei@qq.com
 * @create 2018/3/30
 */
public interface IWfBaseService<T>  extends IService<T> {

    /**
     * 创建草稿
     * @param t
     * @return
     */
    String saveDraft(T t);

    /**
     * 发起流程
     * @param startWfVo
     * @return
     */
    boolean submitDraft(StartWfVo startWfVo);

    /**
     * 审批通过
     * @param passWfVo
     * @return
     */
    boolean pass(PassWfVo passWfVo);

    /**
     * 审批不通过
     * @param passWfVo
     * @return
     */
    boolean noPass(PassWfVo passWfVo);

    /**
     * 发布
     * @param t
     * @return
     */
    boolean publish(T t);

    /**
     * 取消发布
     * @param t
     * @return
     */
    boolean unPublish(T t);
    


}
