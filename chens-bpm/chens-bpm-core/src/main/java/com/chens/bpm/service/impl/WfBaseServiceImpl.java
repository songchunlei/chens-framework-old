package com.chens.bpm.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.bpm.enums.WfStatus;
import com.chens.bpm.service.IWfBaseService;
import com.chens.bpm.service.IWfEngineService;
import com.chens.bpm.vo.WfBaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 流程引擎基础服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/30
 */
public class WfBaseServiceImpl<M extends BaseMapper<T>, T extends WfBaseEntity<T>> extends ServiceImpl<M , T>  implements IWfBaseService<T>{

    @Autowired
    IWfEngineService wfEngineService;

    /**
     * 创建草稿
     * @param t
     * @return
     */
    @Override
    @Transactional
    public String createDraft(T t)
    {
        t.setStatus(WfStatus.WAITING.getCode());
        this.insert(t);
        return t.getId();
    }

    /**
     * 提交
     * @param t
     * @return
     */
    @Override
    @Transactional
    public boolean submitDraft(T t) {

        t.setStatus(WfStatus.CHECKING.getCode());
        this.insertOrUpdate(t);
        wfEngineService.startWorkflow();
        return true;

    }

    /**
     * 审批通过
     * @param t
     * @return
     */
    @Override
    @Transactional
    public boolean pass(T t)
    {
        t.setStatus(WfStatus.CHECKING.getCode());
        wfEngineService.passTask();
        return this.updateById(t);
    }

    /**
     * 审批不通过
     * @param t
     * @return
     */
    @Override
    @Transactional
    public boolean noPass(T t)
    {
        t.setStatus(WfStatus.CHECKING.getCode());
        wfEngineService.unPassTask();
        return this.updateById(t);
    }




}
