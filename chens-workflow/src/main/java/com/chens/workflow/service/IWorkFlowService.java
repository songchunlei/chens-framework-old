package com.chens.workflow.service;

/**
 * 流程引擎服务接口
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/17
 */
public interface IWorkFlowService {

    boolean start();

    boolean complete();
}
