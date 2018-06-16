package com.chens.bpm.demo.controller;

import com.chens.bpm.demo.entity.DemoForm;
import com.chens.bpm.demo.sync.MyBean;
import com.chens.bpm.demo.sync.MyJavaDelegate;
import com.chens.bpm.demo.util.WfUtil;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 任务
 * 任务候选人（组）：这个任务可以被抢单，任何人都能抢单审批
 * 任务持有人：当前需要做任务的人，直接指定
 * 任务代理人：这任务交给别人做（被委托人）
 * 可以抛开流程表的identi组件，用业务系统的用户id，角色（角色组id）代替
 *
 * @author songchunlei@qq.com
 * @create 2018/6/9
 */
@Controller
@RequestMapping("/task")
public class TaskController extends BaseController{
    /**
     * 任务服务
     */
    @Autowired
    private TaskService taskService;

    /**
     * 存储服务
     */
    @Autowired
    private RepositoryService repositoryService;

    /**
     * 运行时服务
     */
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private MyJavaDelegate myJavaDelegate;

    @Autowired
    private MyBean myBean;

    /**
     * 绑定候选任务组(存act_ru_identitylink中间表)
     */
    @GetMapping("/group")
    public ResponseEntity<Result> group(String taskId,String groupId){
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //设置候选任务
        taskService.addCandidateGroup(taskId,groupId);

        return doSuccess("节点id："+task.getId());
    }

    /**
     * 设置持有人(act_ru_task的owner)
     * @param taskId
     * @param userId
     * @return
     */
     @GetMapping("/owner")
     public ResponseEntity<Result> owner(String taskId,String userId){
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        taskService.setOwner(taskId,userId);
        return doSuccess("节点id："+task.getId());
     }

    /**
     * 设置代理人(act_ru_task的Assginee)
     * @param taskId
     * @param userId
     * @return
     */
    @GetMapping("/assignee")
    public ResponseEntity<Result> assignee(String taskId,String userId){
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        taskService.claim(taskId,userId);
        return doSuccess("节点id："+task.getId());
    }

    /**
     * 获取用户有权限处理的task(候选)
     */
    @GetMapping("/taskCandiByUserId")
    public ResponseEntity<Result> taskCandiByUserId(String userId){
        List<Task> taskList = taskService.createTaskQuery().taskCandidateOrAssigned(userId).list();
        return doSuccess(taskList);
    }

    /**
     * 获取用户持有哪些任务
     */
    @GetMapping("/taskOwnerByUserId")
    public ResponseEntity<Result> taskOwnerByUserId(String userId){
        List<Task> taskList = taskService.createTaskQuery().taskOwner(userId).list();
        return doSuccess(taskList);
    }

    /**
     * 获取用户持有哪些代理任务
     */
    @GetMapping("/taskAssigneeByUserId")
    public ResponseEntity<Result> taskAssigneeByUserId(String userId){
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(userId).list();
        return doSuccess(taskList);
    }

    /**
     * 设置参数
     * @param taskId
     * @return
     */
    @GetMapping("/setVar")
    public ResponseEntity<Result> setVar(String taskId){
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //保存单个（act_ru_variable）
        taskService.setVariable(taskId,"varName","值");
        //保存对象(全量表单)
        DemoForm demoForm = new DemoForm("1","scl",29);
        taskService.setVariable(taskId,"demoForm",demoForm);
        //获取
        DemoForm demoForm1 = taskService.getVariable(taskId,"demoForm",DemoForm.class);
        return doSuccess("设置成功",demoForm1);
    }

    /**
     * 参数作用域
     * @param taskId
     * @return
     */
    @GetMapping("/scope")
    public ResponseEntity<Result> scope(String taskId){
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //设置本地参数，这个参数向下流转就找不到了
        taskService.setVariableLocal(taskId,"days",3);
        //setVariable是全局
        return doSuccess("设置成功",taskId);
    }


    /**
     * 任务附件
     * @param taskId 任务id
     * @param prodInsId 流程实例id
     * @return
     */
    @GetMapping("/file")
    public ResponseEntity<Result> file(String taskId,String prodInsId){
        Attachment attachment = taskService.createAttachment("png",taskId,prodInsId,"测试图片","测试图片描述","www.baidu.com");
        taskService.saveAttachment(attachment);
        //setVariable是全局
        return doSuccess("保存成功");
    }


    /**
     * Service Task使用 Delegate expression:${MyService}
     * @return
     */
    @GetMapping("/delegateExpression")
    public ResponseEntity<Result> delegateExpression(){


        ProcessDefinition processDefinition = WfUtil.deploy(repositoryService,"ServiceDelegateExpressionProcess.bpmn20.xml");

        Map<String,Object> vars = new HashMap<>();
        vars.put("MyService",myJavaDelegate);

        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(),vars);

        //setVariable是全局
        return doSuccess("保存成功");
    }

    /**
     * Service Task
     * 使用 expression 直接调用自定义服务方法 $(myBean.print(execution))
     * 使用 expression 直接调用自定义服务方法取值 ${execution.setVariable('myName',myBean.name)}
     * @return
     */
    @GetMapping("/expression")
    public ResponseEntity<Result> expression(){


        ProcessDefinition processDefinition = WfUtil.deploy(repositoryService,"MyBeanTask.bpmn20.xml");

        Map<String,Object> vars = new HashMap<>();
        vars.put("myBean",myBean);


        //测试流程引擎反射bean原理-获取实例对象
        Object obj = vars.get("myBean");
        //获取对象对应的class
        Class clazz = obj.getClass();
        try {
            //反射调用
            Method method = clazz.getDeclaredMethod("print",Execution.class);
            method.invoke(obj,new ExecutionEntityImpl());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(),vars);

        System.out.println("已启动流程:"+processInstance.getId()+" var:"+runtimeService.getVariable(processInstance.getId(),"myName"));

        //setVariable是全局
        return doSuccess("发布成功");
    }

    /**
     * Service Task使用 activiti:type="shell"()
     * 定义Class Field
     * @return
     */
    @GetMapping("/shell")
    public ResponseEntity<Result> shell(){


        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"ShellProcess.bpmn20.xml",null);

        System.out.println("已启动流程:"+processInstance.getId()+" var:"+runtimeService.getVariable(processInstance.getId(),"reslist"));



        return doSuccess("保存成功");
    }

    /**
     * 手工任务，类似抛出，自动执行
     * @return
     */
    @GetMapping("/manual")
    public ResponseEntity<Result> manual(){


        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"ShellProcess.bpmn20.xml",null);

        System.out.println("已启动流程:"+processInstance.getId()+" var:"+runtimeService.getVariable(processInstance.getId(),"reslist"));



        return doSuccess("保存成功");
    }

    /**
     * 邮件任务
     * 需要配置邮件服务器
     * @return
     */
    @GetMapping("/mail")
    public ResponseEntity<Result> mail(){


        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"ShellProcess.bpmn20.xml",null);

        System.out.println("已启动流程:"+processInstance.getId()+" var:"+runtimeService.getVariable(processInstance.getId(),"reslist"));



        return doSuccess("保存成功");
    }





}
