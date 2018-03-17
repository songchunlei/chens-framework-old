package com.chens.workflow.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chens.core.web.BaseController;

/**
 * 流程服务控制器
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/17
 */
@RestController
@RequestMapping("/workflow")
public class WorkFlowController extends BaseController {
	
/*	 @Autowired
	 private RepositoryService repositoryService;
	 
	 @Autowired
	 private IWorkFlowService workFlowService;
	
	 @RequestMapping("/deployWorkflow")
	 public String deployWorkflow(){
	      Deployment deployment = repositoryService
	                    .createDeployment()
	                    .name("请假流程")  //流程名称
	                    .addClasspathResource("bpm/demo.bpmn")  //文件路径
	                    .addClasspathResource("bpm/demo.png")   //流程图片文件路径
	                    .deploy();
	     return "流程ID" + deployment.getId();
	  }
	 
	 @RequestMapping("/startWorkflow")
	 public ResponseEntity<Result> startWorkflow(){
		 WorkFlowRequestParam workFlowRequestParam = new WorkFlowRequestParam();
		 workFlowRequestParam.setStartUserId("wudepeng");
		 workFlowRequestParam.setBusinessKey("AASSSAAAASEW");
		 workFlowRequestParam.setNextUserId("wdp");
		 workFlowRequestParam.setProcessDefinitionKey("DEMO_LEAVE");
		 WorkFlowReturn workFlowReturn = workFlowService.startWorkflow(workFlowRequestParam);
		 if(workFlowReturn.isStartSuccess()){
			 return doSuccess(workFlowReturn.getMessage());
		 }else{
			 return doError(workFlowReturn.getMessage());
		 }	    
	  }
	 
	 @RequestMapping("/completeTask")
	 public ResponseEntity<Result> completeTask(){
		 WorkFlowRequestParam workFlowRequestParam = new WorkFlowRequestParam();
		 workFlowRequestParam.setBpmReason("同意");
		 workFlowRequestParam.setTaskId("7509");
		 workFlowRequestParam.setBusinessKey("AASSSAAAASE");
		 workFlowRequestParam.setNextUserId("zyl");
		 WorkFlowReturn workFlowReturn = workFlowService.completeTask(workFlowRequestParam);
		 if(workFlowReturn.isCompleteSuccess()){
			 return doSuccess(workFlowReturn.getMessage());
		 }else{
			 return doError(workFlowReturn.getMessage());
		 }	    
	  }*/
}
