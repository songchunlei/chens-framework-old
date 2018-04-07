package com.chens.bpm.vo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
/**
 * 流程返回参数实体类
 * @author WDP
 *
 */
public class WorkFlowReturn implements Serializable {

	private static final long serialVersionUID = -8606164411074390725L;
	
	private boolean startSuccess;//发起成功标记
	
	private boolean completeSuccess;//任务完成成功标记
	
	private boolean finish;//流程结束标记
	
	private String existFlag;//是否已存在，Y是，
	
	private String message;//消息
	
	private JSONObject data;//返回的数据
	
	

	public WorkFlowReturn() {
		super();
	}


	public WorkFlowReturn(boolean startSuccess, String message, JSONObject data) {
		super();
		this.startSuccess = startSuccess;
		this.message = message;
		this.data = data;
	}
	

	public WorkFlowReturn(boolean completeSuccess, boolean finish, String message, JSONObject data) {
		super();
		this.completeSuccess = completeSuccess;
		this.finish = finish;
		this.message = message;
		this.data = data;
	}



	public boolean isStartSuccess() {
		return startSuccess;
	}

	public void setStartSuccess(boolean startSuccess) {
		this.startSuccess = startSuccess;
	}

	public boolean isCompleteSuccess() {
		return completeSuccess;
	}

	public void setCompleteSuccess(boolean completeSuccess) {
		this.completeSuccess = completeSuccess;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}


	public String getExistFlag() {
		return existFlag;
	}


	public void setExistFlag(String existFlag) {
		this.existFlag = existFlag;
	}
	
	

}
