package com.chens.bpm.demo.controller;

import com.chens.core.constants.CommonConstants;
import com.chens.core.util.BaseFileUtil;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.zip.ZipInputStream;

/**
 * 发布(用GetMapping是为了测试省事，不要这么用)
 *
 * @author songchunlei@qq.com
 * @create 2018/1/3
 */
@Controller
@RequestMapping("/deploy")
public class DeployController extends BaseController{

    /**
     * 存储服务
     */
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Value(value="classpath:date.zip")
    private Resource resource;

    /**
     * zip上传发布
     * @return
     */
    @GetMapping("/zip")
    public ResponseEntity<Result> zip() throws IOException {

        DeploymentBuilder builder = repositoryService.createDeployment();
        ZipInputStream zipInputStream = new ZipInputStream(resource.getInputStream());
        builder.addZipInputStream(zipInputStream);
        builder.deploy();
        return doSuccess("发布");
    }

    /**
     * bpmn上传发布
     * @return
     */
    @GetMapping("/bpmn")
    public ResponseEntity<Result> bpmn() throws FileNotFoundException {
        DeploymentBuilder builder = repositoryService.createDeployment();
        Deployment deployment = builder.addBpmnModel("My Test Process",createModel()).deploy();
        return doSuccess("发布:"+deployment.getId());
    }

    /**
     * 关闭部署验证
     * @return
     */
    @GetMapping("/novalidate")
    public ResponseEntity<Result> novalidate() throws FileNotFoundException {
        DeploymentBuilder builder = repositoryService.createDeployment();
        builder.addBpmnModel("My Test Process",createModel()).deploy();
        //关闭bpmn校验
        builder.disableBpmnValidation();
        //关闭xml格式校验
        builder.disableSchemaValidation();
        return doSuccess("发布");
    }


    /**
     * 获取流程图
     * @return
     */
    @GetMapping("/pic")
    public void queryRes(HttpServletRequest request, HttpServletResponse response,String deployId) throws IOException {
        //获取流程定义
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployId).singleResult();
        //获取流程图
        InputStream inputStream = repositoryService.getProcessDiagram(definition.getId());

        byte[] buff = new byte[inputStream.available()];
        inputStream.read(buff);
        BaseFileUtil.printResponseOut(request,response,buff,"图片","wf.png", CommonConstants.CHARACTER_UTF8,false);
    }

    /**
     * 删除部署
     * @return
     */
    @GetMapping("/delete")
    public void delete(String deployId) {
        //第二个参数为级连删除，如果true，则会删除历史等流程关联数据
        repositoryService.deleteDeployment(deployId,true);
    }

    /**
     * 中止，被中止的不能启动流程实例
     * @return
     */
    @GetMapping("/sup")
    public ResponseEntity<Result> sup(String key) {
        repositoryService.suspendProcessDefinitionByKey(key);
        //测试流程实例能不能启动
        runtimeService.startProcessInstanceByKey(key);
        return doSuccess("中止成功");
    }

    /**
     * 激活中止的流程实例
     * @return
     */
    @GetMapping("/act")
    public ResponseEntity<Result> act(String key) {
        repositoryService.activateProcessDefinitionByKey(key);
        //测试流程实例能不能启动
        runtimeService.startProcessInstanceByKey(key);
        return doSuccess("重新激活成功");
    }

    /**
     * 手工创建流程模型
     * @return
     */
    private BpmnModel createModel(){

        //流程
        Process process =new Process();
        process.setId("Test");
        process.setName("测试");
        //开始事件
        StartEvent startEvent = new StartEvent();
        startEvent.setId("startEvent");
        process.addFlowElement(startEvent);
        //用户任务
        UserTask userTask = new UserTask();
        userTask.setId("userEvent");
        userTask.setName("用户操作测试");
        process.addFlowElement(userTask);
        //结束事件
        EndEvent endEvent = new EndEvent();
        endEvent.setId("endEvent");
        endEvent.setName("结束");
        process.addFlowElement(endEvent);
        //添加顺序流
        process.addFlowElement(new SequenceFlow("startEvent","userEvent"));
        process.addFlowElement(new SequenceFlow("userEvent","endEvent"));
        //放入模型
        BpmnModel model = new BpmnModel();
        model.addProcess(process);
        return model;
    }
}
