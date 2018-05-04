package com.chens.bpm.activiti.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.plugins.Page;
import com.chens.bpm.activiti.mapper.ActivitiMapper;
import com.chens.bpm.constants.BpmConstants;
import com.chens.bpm.exception.BpmException;
import com.chens.bpm.exception.BpmExceptionEnum;
import com.chens.bpm.vo.*;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.ExclusiveGateway;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.chens.bpm.activiti.util.StreamUtils;
import com.chens.bpm.activiti.util.XmlActivitiUtil;
import com.chens.bpm.enums.ConditionEnum;
import com.chens.bpm.enums.WfStatus;
import com.chens.bpm.enums.WorkFlowEnum;
import com.chens.bpm.enums.WorkFlowGlobals;
import com.chens.bpm.service.IWfEngineService;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;

/**
 * 基于activiti实现流程服务
 *
 * @author wudepeng
 * @create 2018/3/30
 */
@Service
public class ActivitiServiceImpl implements IWfEngineService {

    @Autowired
    private ActivitiMapper activitiMapper;
	@Autowired
    private TaskService taskService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;
    @Autowired
    ProcessEngineFactoryBean processEngine;

	@Override
	public WorkFlowReturn startWorkflow(String businessKey,StartWfVo startWfVo) {

        WorkFlowReturn workFlowReturn = new WorkFlowReturn();
        String processDefinitionKey = startWfVo.getProcessDefinitionKey();

        //流程变量
        Map<String,Object> variables = new HashMap<String, Object>(10);

        //下一个环节处理人 若无默认为流程发起人
        String nextUserId = startWfVo.getNextUserId();

        //下一个节点
        String nextTaskKey = startWfVo.getNextTaskKey();

        //放入下一个流程节点
        variables.put(WorkFlowEnum.BPM_ADVICE.getCode(), nextTaskKey);

        //放入下一个用户
        variables.put("nextUserId", nextUserId);

        List<String> list = Arrays.asList(nextUserId.split(","));

        //启动时判断节点是否是会签节点
        if(this.checkStartNextUserTaskIsHuiQian(processDefinitionKey,nextTaskKey)){
            variables.put("assigneeUserIdList", list);
        }else{
            if(list.size() > 1){
                throw new BpmException(BpmExceptionEnum.SINGLE_TASK_SELECT_USER_ERROR);
            }else{
                variables.put("nextUserId", nextUserId);
            }
        }

        /*考虑国际化 这个 应该要存在字典表中 流程状态 1处理中 2完成 3审核不通过 4，驳回修改，5 发起人删除，6 管理员关闭/
        variables.put(WorkFlowGlobals.BPM_STATUS.toString(), WfStatus.CHECKING.getCode());
        标准变量
        variables.put(WorkFlowGlobals.BPM_DATA_ID.toString(), businessKey);
        */

        //校验是否已发起流程
        List<ProcessInstance> piList = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).list();
        if(CollectionUtils.isNotEmpty(piList)){
            throw new BpmException(BpmExceptionEnum.WORKFLOW_IS_STARTED);
        }

        // 设置流程发起人
        identityService.setAuthenticatedUserId(startWfVo.getStartUserId());
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
        if(processInstance == null){
            throw new BaseException(BpmExceptionEnum.WORKFLOW_START_FAIL);
        }

        JSONObject obj = new JSONObject();
        obj.put(BpmConstants.KEY_PROCESS_DEFINITION_ID, processInstance.getProcessDefinitionId());
        obj.put(BpmConstants.KEY_PROCESS_DEFINITION_NAME, processInstance.getProcessDefinitionName());
        obj.put(BpmConstants.KEY_PROCESS_DEFINITION_VERSION, processInstance.getProcessDefinitionVersion());
        obj.put(BpmConstants.KEY_PROCESS_INSTANCE_ID, processInstance.getId());
        workFlowReturn.setStartSuccess(true);
        workFlowReturn.setMessage("流程发起成功");
        workFlowReturn.setData(obj);
        return workFlowReturn;
	}
	
	@Override
   	public boolean checkStartNextUserTaskIsHuiQian(String processDefinitionKey, String nextTaskKey) {
           ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).orderByProcessDefinitionVersion().desc().list().get(0);       
           InputStream resourceAsStream = repositoryService.getResourceAsStream(pd.getDeploymentId(), pd.getResourceName());
           String xmlString = StreamUtils.inputStreamToString(resourceAsStream);
           Map<String,String> startEvent =  XmlActivitiUtil.parseStartXml(xmlString);
           String processDefinitionId = pd.getId();
           //获取流程xml模型
           BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
           FlowNode currentFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(startEvent.get("id"));
           //获取当前节点
           List<SequenceFlow> outSequenceFlowList = currentFlowNode.getOutgoingFlows();
           //获取当前节点的出线分支
           String taskDefinitionKey =  getNextUserTaskNode(outSequenceFlowList,nextTaskKey);
           return checkStartUserTaskIsHuiQian(processDefinitionKey,taskDefinitionKey);
    }

    /**
     * 判断是否会签
     * @param processDefinitionKey
     * @param taskkey
     * @return
     */
    public boolean checkStartUserTaskIsHuiQian(String processDefinitionKey,String taskkey){
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).orderByProcessDefinitionVersion().desc().list().get(0);
        InputStream resourceAsStream = repositoryService.getResourceAsStream(pd.getDeploymentId(), pd.getResourceName());
        String xmlString = StreamUtils.inputStreamToString(resourceAsStream);
        return XmlActivitiUtil.parseXml(xmlString, taskkey);
    }
	
	/**
     * 
     * @Description: 获取下一个usertask接线
     * @param sequenceFlowList
     * @param nextTaskKey  下一个节点Id
     * String 返回类型
     * 
     * @author shenbo
     */
    private String getNextUserTaskNode(List<SequenceFlow> sequenceFlowList,String nextTaskKey) {
        if(CollectionUtils.isNotEmpty(sequenceFlowList)){
            for(SequenceFlow flow : sequenceFlowList){
                //判断是否是排他网关
                if(flow.getTargetFlowElement() instanceof ExclusiveGateway){
                   ExclusiveGateway exclusiveGateway = (ExclusiveGateway)flow.getTargetFlowElement();
                   String taskDefinitionKey =  getNextUserTaskNode(exclusiveGateway.getOutgoingFlows(),nextTaskKey);
                   if(StringUtils.isNotBlank(taskDefinitionKey)){
                	   return taskDefinitionKey;
                   }
                }else{
                     String condition =  (String)flow.getConditionExpression();
                     if(StringUtils.isNotBlank(condition)){
                        String[] cds = condition.split("==");
                        String pvalue = cds[1];
                        if(pvalue.indexOf("\"") >= 0){
                            pvalue = pvalue.substring(1, pvalue.length()-2);
                        }else{
                            pvalue = pvalue.substring(0, pvalue.length()-1);
                        }
                        //确定连线
                        if(StringUtils.equals(nextTaskKey,pvalue)){
                           return flow.getTargetFlowElement().getId();
                        }
                    }else{
                        return flow.getTargetFlowElement().getId();
                    }
                }
            }
        }
        return null; 
    }
    
    /**
     * 判断节点是否为会签节点
     * @param processDefinitionKey 流程key
     * @return 参数描述
     * @author shenbo
     */
    @Override
	public boolean checkActivitiIsHuiQian(String processDefinitionKey) {
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).orderByProcessDefinitionVersion().desc().list().get(0);
        InputStream resourceAsStream = repositoryService.getResourceAsStream(pd.getDeploymentId(), pd.getResourceName());
        String xmlString = StreamUtils.inputStreamToString(resourceAsStream);
        Map<String,String> startEvent =  XmlActivitiUtil.parseStartXml(xmlString);
        Map<String,String> flow = XmlActivitiUtil.parseXml(xmlString,"sequenceFlow","sourceRef",startEvent.get("id"));
        String taskkey = flow.get("targetRef");
        return XmlActivitiUtil.parseXml(xmlString, taskkey);
    }

	@Override
	public WorkFlowReturn completeTask(PassWfVo passVo) {
		WorkFlowReturn workFlowReturn = new WorkFlowReturn();
        String taskId = passVo.getTaskId();
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if(task == null){
        	workFlowReturn.setCompleteSuccess(false);
        	workFlowReturn.setMessage("任务不存在");
        	return workFlowReturn;
        }
        String processInstanceId = task.getProcessInstanceId();


         /*
        Map<String,Object> variables = workFlowRequestParam.getVariables();
        if (null ==variables ) {
            variables = new HashMap<String,Object>();
        }
        将表单实体类型的字段属性作为流程变量传入
        Map<String, Object> formObj = paramsVo.getFormObj();
        variables.putAll(formObj);
        */

        //定义流程变量
        Map<String,Object> variables = new HashMap<>(16);

        //下一个用户Id
        String nextUserId = passVo.getNextUserId();

        //下一个节点Id
        String nextTaskKey = passVo.getNextTaskKey();

        //流程变量-放入下一个节点Id
        variables.put(WorkFlowEnum.BPM_ADVICE.getCode(), nextTaskKey);

        List<String> list = Arrays.asList(nextUserId.split(","));
        //判断当前节点是否为会签节点
        boolean isHuiqian = false;
        if(this.checkUserTaskIsHuiQian(taskId, "")){
            isHuiqian = true;
        }

        //用下一个节点id，判断下个节点是否是会签节点
        boolean nextNodeIsHuiQian = this.checkNextUserTaskIsHuiQian(taskId,nextTaskKey);
        if(nextNodeIsHuiQian){
        	//若果当前节点是会签节点 且是第一个来处理的人 直接塞list 若果不是就拼接 原来的 list
        	if(isHuiqian){
        		Object obj = runtimeService.getVariable(task.getExecutionId(), "nrOfCompletedInstances");
        		Object objAll = runtimeService.getVariable(task.getExecutionId(), "nrOfInstances");
        		int nrOfCompletedInstances= 0;
        		int nrOfInstances = 0;
        		if(obj != null){
        			nrOfCompletedInstances = (Integer)obj;        			
        		}
        		if(objAll != null){
        			nrOfInstances = (Integer)objAll;        			
        		}
        		
        		String conditionExp = getCompletionCondition(taskId,"");       		
        		String condition = "";
        		int index = 0;
        		//是否 ==
        		if(conditionExp.indexOf("==") != -1){
        			condition = ConditionEnum.EQ.getCode();
        			index = conditionExp.indexOf("==") + 2;
        		}else if(conditionExp.indexOf(">") != -1){
        			condition = ConditionEnum.GT.getCode();
        			index = conditionExp.indexOf(">") + 1;
        		}else{
        			condition = ConditionEnum.GE.getCode();
        			index = conditionExp.indexOf(">=") + 2;
        		}
        		conditionExp = conditionExp.substring(index, conditionExp.length()-1).trim();      		
        		float exp = Float.parseFloat(conditionExp);        		
        		float now = (float)(nrOfCompletedInstances + 1)/nrOfInstances;
                //当前节点为会签  且是第一个处理人
        		if(nrOfCompletedInstances == 0){
        			runtimeService.setVariable(processInstanceId, WorkFlowEnum.ASSIGNEE_USER_ID_LIST_TEMP.getCode(),list);
        			//这里需要考虑会签节点每次都只选一个人的情况
        			if(StringUtils.equals(condition, ConditionEnum.EQ.getCode())){
        				//相等
        				if(now == exp){
            				runtimeService.setVariable(processInstanceId, WorkFlowEnum.ASSIGNEE_USER_ID_LIST.getCode(),list);
            			}
        			}else if(StringUtils.equals(condition, ConditionEnum.GT.getCode())){
        				//大于
        				if(now > exp){
            				runtimeService.setVariable(processInstanceId, WorkFlowEnum.ASSIGNEE_USER_ID_LIST.getCode(),list);
            			}
        			}else{
        				//大于等于
        				if(now >= exp){
            				runtimeService.setVariable(processInstanceId, WorkFlowEnum.ASSIGNEE_USER_ID_LIST.getCode(),list);
            			}
        			} 
        		}else{//当前是会签 且非第一个处理人 累加上其他人选择的 处理人同时用set去重
                    List<String> listTemp = (List<String>) runtimeService.getVariable(processInstanceId, WorkFlowEnum.ASSIGNEE_USER_ID_LIST_TEMP.getCode());
        			Set<String> set = new LinkedHashSet<String>();
        			set.addAll(listTemp);
        			set.addAll(list);
        			List<String> list2 = new ArrayList<String> ();  
        			list2.addAll(set);
        			//临时解决 只有当完成的会签任务 + 1 = 需要完成的总数时候才去替换数组  需要修改为判断通过率 2017-06-14
        			if(StringUtils.equals(condition, ConditionEnum.EQ.getCode())){
        				//相等        				
        				if(now == exp){
            				runtimeService.setVariable(processInstanceId, WorkFlowEnum.ASSIGNEE_USER_ID_LIST.getCode(),list2);
            				runtimeService.setVariable(processInstanceId, WorkFlowEnum.ASSIGNEE_USER_ID_LIST_TEMP.getCode(),new ArrayList<String> ());
            			}else{
            				//若还不是最后一个会签任务 就累加 暂存起来
            				runtimeService.setVariable(processInstanceId, WorkFlowEnum.ASSIGNEE_USER_ID_LIST_TEMP.getCode(),list2);
            			}       				
        			}else if(StringUtils.equals(condition, ConditionEnum.GT.getCode())){
        				//大于        				
        				if(now > exp){
            				runtimeService.setVariable(processInstanceId, WorkFlowEnum.ASSIGNEE_USER_ID_LIST.getCode(),list2);
            				runtimeService.setVariable(processInstanceId, WorkFlowEnum.ASSIGNEE_USER_ID_LIST_TEMP.getCode(),new ArrayList<String> ());
            			}else{
            				//若还不是最后一个会签任务 就累加 暂存起来
            				runtimeService.setVariable(processInstanceId, WorkFlowEnum.ASSIGNEE_USER_ID_LIST_TEMP.getCode(),list2);
            			}
        			}else{
        				//大于等于        				
        				if(now >= exp){
            				runtimeService.setVariable(processInstanceId, WorkFlowEnum.ASSIGNEE_USER_ID_LIST.getCode(),list2);
            				runtimeService.setVariable(processInstanceId, WorkFlowEnum.ASSIGNEE_USER_ID_LIST_TEMP.getCode(),new ArrayList<String> ());
            			}else{
            				//若还不是最后一个会签任务 就累加 暂存起来
            				runtimeService.setVariable(processInstanceId, WorkFlowEnum.ASSIGNEE_USER_ID_LIST_TEMP.getCode(),list2);
            			}
        			} 
        		}
        	}else{
        		variables.put(WorkFlowEnum.ASSIGNEE_USER_ID_LIST.getCode(), list);
        	}
        }else{
        	if(list.size() > 1){
                throw new BpmException(BpmExceptionEnum.SINGLE_TASK_SELECT_USER_ERROR);
        	}
            variables.put(WorkFlowEnum.NEXT_USER_ID.getCode(), nextUserId);
        }
       
        try {
            //如果当前节点是会签  且当前这个办理人不同意 ，那么删除其他任务 然后完成这个任务  就直接走不同意的流程 一票否决
            if ("0".equals(nextTaskKey) && isHuiqian) {
                List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
                for (Task task1 : taskList) {
                    if (!task1.getId().equals(taskId)) {
                        //删不掉，变通下，其他未完成任务 直接自动完成掉
                        taskService.complete(task1.getId(), variables);
                    }
                }
            }
            taskService.complete(taskId, variables);
            //使用流程实例ID查询
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                            .processInstanceId(processInstanceId)
                            .singleResult();		
            //表示已经完成
            if(processInstance == null){
            	workFlowReturn.setCompleteSuccess(true);
            	workFlowReturn.setFinish(true);
            	workFlowReturn.setMessage("办理成功");
            	return workFlowReturn;
            }
        } catch (Exception e) {
            e.printStackTrace();
        	throw new BpmException(BpmExceptionEnum.WORKFLOW_COMPLETE_FAIL);

        }
        workFlowReturn.setCompleteSuccess(true);
    	workFlowReturn.setFinish(false);
    	workFlowReturn.setMessage("办理成功");
        return workFlowReturn;
    }
	
	private String getCompletionCondition(String taskId ,String taskDefinitionKey){
    	HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        if (StringUtils.isBlank(taskDefinitionKey)) {
        	taskDefinitionKey = historicTaskInstance.getTaskDefinitionKey();
        }
        String processDefinitionId = historicTaskInstance.getProcessDefinitionId();
        ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processDefinitionId);
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getResourceName());
        String xmlString = StreamUtils.inputStreamToString(resourceAsStream);
        return XmlActivitiUtil.getCompletionCondition(xmlString, taskDefinitionKey);
    }
	
	@Override
	public boolean checkUserTaskIsHuiQian(String taskId, String taskDefinitionKey) {
		HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        if(StringUtils.isBlank(taskDefinitionKey)) {
        	taskDefinitionKey = historicTaskInstance.getTaskDefinitionKey();
        }
        String processDefinitionId = historicTaskInstance.getProcessDefinitionId();
        ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processDefinitionId);
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getResourceName());
        String xmlString = StreamUtils.inputStreamToString(resourceAsStream);
        return XmlActivitiUtil.parseXml(xmlString, taskDefinitionKey);
	}
	
	 /**
     * 校验下一个节点是否是会签节点
     * @param taskId
     * @param nextNodeId 下一个节点id
     * @return boolean 返回类型
     */
    private boolean checkNextUserTaskIsHuiQian(String taskId, String nextNodeId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processDefinitionId = task.getProcessDefinitionId();
        String processInstanceId = task.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        String activityId = processInstance.getActivityId();
        if(StringUtils.isBlank(activityId)){
            List<Execution> executionList = runtimeService.createExecutionQuery().parentId(processInstance.getId()).list();
            if(CollectionUtils.isNotEmpty(executionList)){
                activityId = executionList.get(0).getActivityId();
            }
        }
        //获取流程xml模型
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        //获取当前节点
        FlowNode currentFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(activityId);
        //获取当前节点的出线分支
        List<SequenceFlow> outSequenceFlowList = currentFlowNode.getOutgoingFlows();
        String taskDefinitionKey =  getNextUserTaskNode(outSequenceFlowList,nextNodeId);
        return checkUserTaskIsHuiQian(taskId,taskDefinitionKey);
    }

	@Override
	public String getProcessStarterByTaskId(String taskId) {
	   Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
       if(task == null){
        	return null;
       }
       String processInstanceId = task.getProcessInstanceId();
       //使用流程实例ID查询
       ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
               .processInstanceId(processInstanceId)
               .singleResult();
       if(processInstance == null){
    	   return null;
       }else{
    	   return processInstance.getStartUserId();
       }
	}

    @Override
    public Page<MyTodoTask> getMyTodoTaskPage(Page<MyTodoTask> page, MyTodoTask myTodoTask) {
        page.setRecords(activitiMapper.getMyTodoTaskPage(page, myTodoTask));
        return page;
    }

    @Override
    public Page<MyDoneTask> getMyDoneTaskPage(Page<MyDoneTask> page, MyDoneTask myDoneTask) {
        page.setRecords(activitiMapper.getMyDoneTaskPage(page, myDoneTask));
        return page;
    }

    @Override
    public Page<MyStartInstance> getMyStartProcessInstancePage(Page<MyStartInstance> page,
                                                               MyStartInstance myStartProcessInstance) {
        page.setRecords(activitiMapper.getMyStartProcessInstancePage(page, myStartProcessInstance));
        return page;
    }

	@Override
	public Map<String, Object> getTaskInfoByTaskId(String taskId) {
		 Map<String, Object> map = new HashMap<String, Object>();
		 Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		 if(task == null){
			 return null; 
		 }else{
			 map.put("taskDefinitionKey", task.getTaskDefinitionKey());
			 map.put("taskDefinitionName", task.getName());
			 map.put("assignee", task.getAssignee());
			 return map;
		 }
		
	}

}
