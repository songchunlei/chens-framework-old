package com.chens.bpm.service;

import com.chens.bpm.vo.WorkFlowRequestParam;
import com.chens.bpm.vo.WorkFlowReturn;

//import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 调用流程引擎接口(暂时不走feign)
 * @auther songchunlei
 * @create 2018/3/30
 */
//@FeignClient(path = "wfEngine",value = "chens-bpm-service")
public interface IWfEngineService {


	 /**
     * 发起流程
     * @param workFlowRequestParam
     * @return
     */
	WorkFlowReturn startWorkflow(WorkFlowRequestParam<?> workFlowRequestParam);
	
    /**
     * 发起节点校验下一个节点是否是会签节点
     * @param processDefinitionKey
     * @param field
     * @param value
     * @return 参数描述
     * boolean 返回类型
     */
    boolean checkStartNextUserTaskIsHuiQian(String processDefinitionKey, String field,String value);
  

    /**
     * 
     *@Description: 判断节点是否为会签节点
     */
    boolean checkActivitiIsHuiQian(String processDefinitionKey);
    
    /**
	 * 提交处理任务
	 * @param workFlowRequestParam
	 * @return
	 */
	WorkFlowReturn completeTask(WorkFlowRequestParam<?> workFlowRequestParam);
	
	/**
     * @Description: 判断用户任务节点是否为会签节点
     * @param taskId
     * @param taskkey
     */
    boolean checkUserTaskIsHuiQian(String taskId ,String taskkey);

    /**
     * 不同意当前任务
     */
    void unPassTask();
    
    /**
     * 通过任务Id获取流程的发起人
     * @param taskId
     * @return
     */
    String getProcessStarterByTaskId(String taskId);
}
