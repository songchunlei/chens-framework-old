package com.chens.bpm.service;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.chens.bpm.vo.*;

/**
 * 调用流程引擎接口(暂时不走feign)
 * @author songchunlei
 * @create 2018/3/30
 */
public interface IWfEngineService {


    /**
     * 发起流程
     * @param businessKey 业务id
     * @param startWfVo 发起流程变量
     * @return
     */
	WorkFlowReturn startWorkflow(String businessKey,StartWfVo startWfVo);
	
    /**
     * 发起节点校验下一个节点是否是会签节点
     * @param processDefinitionKey
     * @param nextNodeId
     * @return 参数描述
     * boolean 返回类型
     */
    boolean checkStartNextUserTaskIsHuiQian(String processDefinitionKey, String nextNodeId);


    /**
     * 判断节点是否为会签节点
     * @param processDefinitionKey
     * @return
     */
    boolean checkActivitiIsHuiQian(String processDefinitionKey);
    
    /**
	 * 提交处理任务
	 * @param passVo
	 * @return
	 */
	WorkFlowReturn completeTask(PassWfVo passVo);
	
	/**
     * @Description: 判断用户任务节点是否为会签节点
     * @param taskId
     * @param taskkey
     */
    boolean checkUserTaskIsHuiQian(String taskId ,String taskkey);
    
    /**
     * 通过任务Id获取流程的发起人
     * @param taskId
     * @return
     */
    String getProcessStarterByTaskId(String taskId);


    /**
     * 我的待办分页
     * @param page
     * @param myTodoTask
     * @return
     */
    Page<MyTodoTask> getMyTodoTaskPage(Page<MyTodoTask> page, MyTodoTask myTodoTask);

    /**
     * 我的已办分页
     * @param page
     * @param myDoneTask
     * @return
     */
    Page<MyDoneTask> getMyDoneTaskPage(Page<MyDoneTask> page, MyDoneTask myDoneTask);


    /**
     * 我的申请/我发起的流程分页
     * @param page
     * @param myStartProcessInstance
     * @return
     */
    Page<MyStartInstance> getMyStartProcessInstancePage(Page<MyStartInstance> page, MyStartInstance myStartProcessInstance);


    /**
     * 根据任务id获取任务信息
     * @param taskId
     * @return
     */
    Map<String, Object> getTaskInfoByTaskId(String taskId);
}
