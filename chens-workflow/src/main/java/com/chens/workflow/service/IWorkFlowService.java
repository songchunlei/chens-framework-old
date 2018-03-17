package com.chens.workflow.service;

import com.chens.core.entity.workflow.WorkFlowRequestParam;
import com.chens.core.entity.workflow.WorkFlowReturn;

/**
 * 流程引擎服务接口
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/17
 */
public interface IWorkFlowService {
	

    /**
     * 
     *@Description: 判断节点是否为会签节点
     */
    public boolean checkActivitiIsHuiQian(String processDefinitionKey);
	
    /**
     * 发起流程
     * @param workFlowRequestParam
     * @return
     */
	WorkFlowReturn startWorkflow(WorkFlowRequestParam workFlowRequestParam);
	
	/**
	 * 提交处理任务
	 * @param workFlowRequestParam
	 * @return
	 */
	WorkFlowReturn completeTask(WorkFlowRequestParam workFlowRequestParam);
	
	 /**
     * @Description: 判断用户任务节点是否为会签节点
     * @param taskId
     * @param taskkey
     */
    boolean checkUserTaskIsHuiQian(String taskId ,String taskkey);

    boolean start();

    boolean complete();
}
