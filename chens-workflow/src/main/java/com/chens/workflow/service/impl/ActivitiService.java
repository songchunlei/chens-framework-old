package com.chens.workflow.service.impl;

import com.chens.workflow.service.IWorkFlowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 流程服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/17
 */
@Service
@Transactional
public class ActivitiService implements IWorkFlowService {
    @Override
    public boolean start() {
        return false;
    }

    @Override
    public boolean complete() {
        return false;
    }
}
