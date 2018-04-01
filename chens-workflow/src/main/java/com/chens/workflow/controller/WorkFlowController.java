package com.chens.workflow.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chens.core.entity.workflow.WorkFlowRequestParam;
import com.chens.core.entity.workflow.WorkFlowReturn;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import com.chens.workflow.service.IWorkFlowService;

/**
 * 流程服务控制器  demo
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/17
 */
@RestController
@RequestMapping("/workFlowController")
public class WorkFlowController extends BaseController {
	
	 @Autowired
	 private RepositoryService repositoryService;
	 
	 @Autowired
	 private IWorkFlowService workFlowService;
	
	 /**
	  * 部署流程demo
	  * @return
	  */
	 @RequestMapping("/deployWorkflow")
	 public String deployWorkflow(){
	      Deployment deployment = repositoryService
	                    .createDeployment()
	                    .name("样例")  //流程名称
	                    .addClasspathResource("bpm/myProcess.bpmn")  //文件路径
	                    .addClasspathResource("bpm/myProcess.png")   //流程图片文件路径
	                    .deploy();
	     return "流程ID" + deployment.getId();
	  }
	 
	 /**
	  * 发起流程demo
	  * @return
	  */
	 @RequestMapping("/startWorkflow")
	 public ResponseEntity<Result> startWorkflow(){
		 WorkFlowRequestParam workFlowRequestParam = new WorkFlowRequestParam();
		 workFlowRequestParam.setStartUserId("wudepeng");
		 workFlowRequestParam.setBusinessKey("AASSSAAAASEWCDD635888");
		 workFlowRequestParam.setNextUserId("wdp");
		 workFlowRequestParam.setProcessDefinitionKey("SOURCE_APPROVE");
		 WorkFlowReturn workFlowReturn = workFlowService.startWorkflow(workFlowRequestParam);
		 if(workFlowReturn.isStartSuccess()){
			 return doSuccess(workFlowReturn.getMessage(),workFlowReturn.getData());
		 }else{
			 return doError(workFlowReturn.getMessage());
		 }	    
	  }
	 
	 /**
	  * 完成任务方法demo
	  * @return
	  */
	 @RequestMapping("/completeTask")
	 public ResponseEntity<Result> completeTask(){
		 WorkFlowRequestParam workFlowRequestParam = new WorkFlowRequestParam();
		 workFlowRequestParam.setBpmReason("同意");
		 workFlowRequestParam.setTaskId("2509");
		 workFlowRequestParam.setBusinessKey("AASSSAAAASEW");
		 workFlowRequestParam.setNextUserId("zyl");
		 workFlowRequestParam.setVariableValue("node2");
		 WorkFlowReturn workFlowReturn = workFlowService.completeTask(workFlowRequestParam);
		 if(workFlowReturn.isCompleteSuccess()){
			 return doSuccess(workFlowReturn.getMessage());
		 }else{
			 return doError(workFlowReturn.getMessage());
		 }	    
	  }
	 
	 /**
	  * 获取流程图demo
	  * @param processInstanceId
	  * @param response
	  * @throws IOException
	  */
	 @GetMapping("/viewImage")
	 public void viewImage(String processInstanceId, HttpServletResponse response) throws IOException{
		  processInstanceId = "2511";
		  InputStream inputStream = workFlowService.getResourceDiagramImageInputStream(processInstanceId);
		  OutputStream outputStream = response.getOutputStream();
		  for(int b=-1;(b=inputStream.read()) != -1;){
			  outputStream.write(b);
		  }
		  outputStream.close();
		  inputStream.close();    
	  }
	 
	 
	 
}
