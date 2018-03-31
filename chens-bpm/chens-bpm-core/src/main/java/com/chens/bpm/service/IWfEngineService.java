package com.chens.bpm.service;

/**
 * 调用流程引擎接口(是否要坐做成feign请求？)
 * @auther songchunlei
 * @create 2018/3/30
 */
public interface IWfEngineService {

    /**
     * 启动流程
     */
    void startWorkflow();

    /**
     * 完成当前任务
     */
    void passTask();

    /**
     * 不同意当前任务
     */
    void unPassTask();
}
