package com.chens.bpm.enums;

/**
 * @Description: 定义各种常量
 * @version V1.0
 */
public enum WorkFlowEnum {
	START_NODE("发起人节点","startNode"),
	START_USER_ID("流程发起人","startUserId"),
	ASSIGNEE_USER_ID_LIST("流程会签节点的数组名称","assigneeUserIdList"),
	NEXT_USER_ID("流程下一个节点的办理人流程变量名称","nextUserId"),
	ASSIGNEE_USER_ID_LIST_TEMP("流程会签节点的暂存数组名称-- 会签节点的下一个节点也是会签的时候收集前台传过来的用户","assigneeUserIdListTemp");
	private String name;
	private String code;
	private WorkFlowEnum(String name,String code){
		this.name = name;
		this.code = code;
	}
	/**
	 * 根据code 取name
	 * @param code
	 * @return
	 */
   public static String getName(String code) {  
       for (WorkFlowEnum mt : WorkFlowEnum.values()) {  
           if (mt.getCode().equals(code)) {  
               return mt.name;  
           }  
       }  
       return null;  
   } 
/**
 * 根据name取code
 * @param name
 * @return
 */
   public static String getCode(String name) {  
       for (WorkFlowEnum mt : WorkFlowEnum.values()) {  
           if (mt.getName().equals(name)) {  
               return mt.code;  
           }  
       }  
       return null;  
   }
   
   public static WorkFlowEnum getSelf(String code) {  
       for (WorkFlowEnum mt : WorkFlowEnum.values()) {  
           if (mt.getCode().equals(code)) {  
               return mt;  
           }  
       }  
       return null;  
   } 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
