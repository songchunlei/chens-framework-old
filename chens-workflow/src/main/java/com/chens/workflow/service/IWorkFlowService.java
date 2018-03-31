package com.chens.workflow.service;

import java.io.InputStream;

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
    
    /**
     * 发起节点校验下一个节点是否是会签节点
     * @param taskId
     * @param string
     * @return 参数描述
     * boolean 返回类型
     * @throws 异常说明
     */
    public boolean checkStartNextUserTaskIsHuiQian(String processDefinitionKey, String field,String value);
    
    /**
     * 获取流程图
     * @param processInstanceId
     * @return
     */
    public InputStream getResourceDiagramImageInputStream(String processInstanceId);
}
